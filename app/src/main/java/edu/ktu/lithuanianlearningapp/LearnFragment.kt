package edu.ktu.lithuanianlearningapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.ktu.lithuanianlearningapp.models2.WordUser
import edu.ktu.lithuanianlearningapp.viewmodels.WordListFireBasedViewModel


val db = Firebase.firestore

class LearnFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<WordAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learn, container, false)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {

        var wordList: MutableList<Word> = mutableListOf(
            Word("Vienas", "One", "Numbers"),
            Word("Du", "Two", "Numbers"),
            Word("Trys", "Three", "Numbers"),
            Word("Ne", "No", "Main"),
            Word("Taip", "Yes", "Main"),
            Word("Gal", "Maybe", "Conversation"),
            Word("Ačiū", "Thank you", "Main"),
            Word("Prašom", "Welcome", "Main"),
            Word("Keturi", "Four", "Numbers"),
            Word("Padėkite man", "Help me", "Main"),
            Word("Pagalba", "Help (n)", "Main"),
            Word("Vardas", "Name", "About you"),
            Word("Pavardė", "Surname", "About you"),
        ) as MutableList<Word>
        val source = Source.CACHE

        db.collection("words")
            .whereEqualTo("ShowOthers", true)
            .get(source)
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                    // binding.textView8.text
                    wordList.add(
                        Word(
                            document.data.getValue("word_in_Lithuanian").toString(),
                            document.data.getValue("translation").toString(),
                            document.data.getValue("word_field").toString()
                        )
                    )
                    //    Log.d(TAG, "${shuffledWords[shuffledWords.lastIndex].translation} => ${shuffledWords[shuffledWords.lastIndex].type}")
                    // shuffledWords[shuffledWords.lastIndex].translation = document.data.getValue("translation").toString()
                    //  shuffledWords[shuffledWords.lastIndex].type = document.data.getValue("word_in_Lithuanian").toString()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
        Log.d(TAG, "${wordList[wordList.lastIndex].translation} => ${wordList[wordList.lastIndex].word_in_Lithuanian}")

        val currentUser = FirebaseAuth.getInstance().currentUser

        currentUser?.let {
            db.collection("words").whereEqualTo("owner", currentUser.uid)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                        // binding.textView8.text
                        wordList.add(
                            Word(
                                document.data.getValue("word_in_Lithuanian").toString(),
                                document.data.getValue("translation").toString(),
                                document.data.getValue("word_field").toString()
                            )
                        )
                        //    Log.d(TAG, "${shuffledWords[shuffledWords.lastIndex].translation} => ${shuffledWords[shuffledWords.lastIndex].type}")
                        // shuffledWords[shuffledWords.lastIndex].translation = document.data.getValue("translation").toString()
                        //  shuffledWords[shuffledWords.lastIndex].type = document.data.getValue("word_in_Lithuanian").toString()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }





            super.onViewCreated(itemView, savedInstanceState)
            val recycler = view?.findViewById(R.id.word_list2) as RecyclerView
            val button = view?.findViewById(R.id.button11) as Button

          val currentUser = FirebaseAuth.getInstance().currentUser

            val rootRef = FirebaseFirestore.getInstance()
            val subjectsRef = rootRef.collection("words").whereEqualTo("owner", currentUser?.uid)
            val subjectsRef2 = rootRef.collection("words").whereEqualTo("ShowOthers", true)

            val spinner = view?.findViewById(R.id.spinner) as Spinner

            var sk = 0

            var subjects: MutableList<String?> = ArrayList();
            val adapter = ArrayAdapter<String?>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                subjects
            )

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
                subjectsRef.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            val subject = document.getString("word_field")

                            subjects.add(subject)
                            if (subjects.distinct().count() == sk) {
                                subjects.remove(subject)
                            }
                            sk = subjects.distinct().count()
                        }

                        adapter.notifyDataSetChanged()
                    }
                }




            subjects.add("All")
            subjects.add("Main")
            subjects.add("About you")


            var index = 1

            var title = "All";
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val text: String = parent?.getItemAtPosition(position).toString()
                    recycler.adapter = WordAdapter3(index, text, wordList)
                    title = text;
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }

            recycler.adapter = WordAdapter(wordList)

            view?.findViewById<Button>(R.id.button11)
                ?.setOnClickListener {

                    if (button.text == "Unhide") {
                        button.text = "Hide"
                        index = 1;
                        recycler.adapter = WordAdapter3(index, title, wordList)
                        return@setOnClickListener
                    }
                    if (button.text == "Hide") {
                        button.text = "Unhide"

                        index = 0;
                        recycler.adapter = WordAdapter3(index, title, wordList)
                        return@setOnClickListener
                    }
                }
            view?.findViewById<ImageButton>(R.id.imageButton5)
                ?.setOnClickListener {

                    wordList = wordList.shuffled() as MutableList<Word>
                    recycler.adapter = WordAdapter3(index, title, wordList)

                }

        }
    }
}




