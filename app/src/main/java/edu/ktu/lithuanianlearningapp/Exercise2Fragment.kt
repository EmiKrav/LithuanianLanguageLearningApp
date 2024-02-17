package edu.ktu.lithuanianlearningapp

import android.R
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import edu.ktu.lithuanianlearningapp.database.WordDbDatabase
import edu.ktu.lithuanianlearningapp.databinding.FragmentExercise2Binding
import edu.ktu.lithuanianlearningapp.viewmodels.*


class Exercise2Fragment : Fragment() {
    val viewModel: WordExerciseFireBasedViewModel by viewModels()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
                    wordList.add(Word(
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
        wordList.shuffled() as MutableList<WordW>
        var words: MutableList<WordW> = mutableListOf(
            WordW("Gerai", "good"),

            WordW("Kitas", "next"),
        WordW(
            "Toliau","continue"
        ),
        WordW(
            "Pradžia", "begining"
        ),
        WordW(
            "Pabaiga","ending"
        ),
        WordW(
            "Vienas", "one"
        ),
        WordW(
            "Du", "two"
        ),
        WordW(
            "Trys", "three"
        ),
        WordW(
            "Keturi","four"
        ),
        WordW(
            "Penki","five"
        )
,       WordW(wordList[0].word_in_Lithuanian, wordList[0].translation)
        ).shuffled() as MutableList<WordW>
        words.add(  WordW(wordList[0].word_in_Lithuanian, wordList[0].translation))

        // words = words.shuffled() as MutableList<WordW>
       // Log.d(TAG, " CCCCCCCCCCC ${words.count()} ${words[0].translation}")

        val viewModel1: SettingsViewModel by viewModels()

        var Button1 = false
        var Button2= false
        var Button3= false
        var Button4 = 0

        viewModel1.getArg1.observe(viewLifecycleOwner){
             Button1 =  it;
        }
        viewModel1.getArg2.observe(viewLifecycleOwner){
            Button2 =  it;
        }
        viewModel1.getArg3.observe(viewLifecycleOwner){
           Button3 =  it;

        }
        viewModel1.getProgress.observe(viewLifecycleOwner) {
            Button4 =  it;
        }
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                Exercise2FragmentDirections.actionExercise2FragmentToMainScreenFragment(Button4, Button1, Button2, Button3)
            findNavController().navigate(action)
        }


        // Inflate the layout for this fragment
        val binding = FragmentExercise2Binding.inflate(inflater)
        val viewModel: TypingViewModel by viewModels{

            // arba istrini apacia
            TypingViewModelFactory(
                words[0].type,
                words[0].translation,
                words[0].type.length,
                requireActivity().application
            )
           //ir parasai sita
        // TypingViewModelFactory(application = requireActivity().application)
        }


        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner





        viewModel.lose.observe(viewLifecycleOwner, {
            if (it == true) {
                val action =
                    Exercise2FragmentDirections.actionExercise2FragmentToExercise2EndFragment(
                        viewModel.challange,
                        true,
                        viewModel.maxAttempts+1-viewModel.correct.value!!
                    )
                findNavController().navigate(action)
                viewModel.navigatedToLose()
            }
        })


        return binding.root
    }
}

