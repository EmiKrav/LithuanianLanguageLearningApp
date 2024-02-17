package edu.ktu.lithuanianlearningapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.model.Document
import com.google.firebase.ktx.Firebase
import edu.ktu.lithuanianlearningapp.database.WordDbDatabase
import edu.ktu.lithuanianlearningapp.databinding.FragmentExerciseBinding
import edu.ktu.lithuanianlearningapp.models2.Word
import edu.ktu.lithuanianlearningapp.uiadapters.WordAdapterFireBase
import edu.ktu.lithuanianlearningapp.uiadapters.WordAdminAdapterFireBase
import edu.ktu.lithuanianlearningapp.viewmodels.*
import kotlin.random.Random

class ExerciseFragment : Fragment() {
    val db = Firebase.firestore

    fun randomFromList(): String {
        val list = listOf("First", "Over", "Last", "Bring", "Set", "window","three","acres","also",
            "feel","development","greatest","brick","longer","third","construction","must",
            "warn","walk","least","observe","political","promised","missing","snow","up",
            "shore","enter","sum","almost","topic","entirely","before","establish","met",
            "expect","beneath","ability","zero","size","bare","camera","hardly","impossible",
            "vast","thought","bow","buried","carbon","present","star","excitement","season",
            "bound","species","biggest","stand","tower","chemical","thus","important",
            "importance","positive","parts","over","fly","solve","careful","law","safety",
            "joy","organization","fly","liquid","flew","parts","village","bank","doll",
            "possibly","buffalo","neck","tropical","pile","cowboy","motion","wealth","flight",
            "original","saw","tube","victory","mainly","saw","season","off","recently",
            "string","pick","typical","weak","recently","mill","double","increase","front",
            "slide","temperature","origin","blood","hope","equally","spent","board","private",
            "with","sides","green","along","escape","engineer","little","smile","paint","flame",
            "earlier","tell","if","count","using","becoming","thing","breath","news","car",
            "leather","from","active","follow","cent","mouse","importance","seven","managed","use",
            "kept","establish","chair","mouth","shoulder","far","combine","alone","nation","if",
            "bare","refer","motion","farther","produce","travel","wild","living","secret",
            "practice","slope","use","refer","directly","gentle","taught","fire","drew","sleep",
            "wire","ago","stairs","worried","occasionally","feature","know","stove","attempt",
            "go","dog","principal","soon","settle","alive","degree","people","halfway","fall",
            "idea","heat","collect","browser","torn","plant","base","represent","drew",
            "daily","dinner","go","anywhere","shells","pair","loss","empty","perhaps","topic",
            "group","happily","magic","result","practical","boat","structure","plural","cry",
            "poor","after","failed","read","airplane","struggle","indicate","itself","cow")
        val randomIndex = Random.nextInt(list.size);
        return list[randomIndex]
    }


    //list.removeIf { i -> randomElements.contains(i) }
    var wordsE = mutableListOf(
        WordW(
            "Gerai", "good"
        ),
        WordW(
            "Kitas", "next"
        ),
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
    )
    var shuffledWords = wordsE.shuffled() as MutableList<WordW>

    init{
        shuffledWords =ListNaujas()
    }

    fun ListNaujas(): MutableList<WordW> {


        db.collection("words")
            .whereEqualTo("ShowOthers", true)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")

                    // binding.textView8.text
                    shuffledWords.add(
                        WordW(
                        document.data.getValue("translation").toString(),
                        document.data.getValue("word_in_Lithuanian").toString()
                        )
                    )
                    //    Log.d(TAG, "${shuffledWords[shuffledWords.lastIndex].translation} => ${shuffledWords[shuffledWords.lastIndex].type}")
                    // shuffledWords[shuffledWords.lastIndex].translation = document.data.getValue("translation").toString()
                    //  shuffledWords[shuffledWords.lastIndex].type = document.data.getValue("word_in_Lithuanian").toString()
                   shuffledWords.shuffled() as MutableList<WordW>
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        val currentUser = FirebaseAuth.getInstance().currentUser

        currentUser?.let {
            db.collection("words").whereEqualTo("owner", currentUser.uid)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                        // binding.textView8.text
                        shuffledWords.add(
                            WordW(
                                document.data.getValue("translation").toString(),
                                document.data.getValue("word_in_Lithuanian").toString()
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
        }


            return shuffledWords
    }

    // Views that are used to show a word’s info
    private lateinit var binding : FragmentExerciseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        shuffledWords.shuffled() as MutableList<WordW>

        // Inflate the layout for this fragment
        binding = FragmentExerciseBinding.inflate(inflater)

        // Set the fragment variable to enable click listeners on buttons
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        // Return the root view of the binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Once the views are created, show the current word

       //

        binding.button10.isVisible = false
        setCheckedChangedListener()
        showWord()
    }
    private var wordindex = 0

    // Set word data to each of the views in the layout
    private fun showWord() {

        binding.textView7.text = "${(1 + wordindex)}/${(shuffledWords.lastIndex + 1)}"
        WordDbDatabase.getInstance(
            requireContext()
        )

        binding.textView8.text = shuffledWords[wordindex].type
        var randomElements2 = listOf(randomFromList(), randomFromList(),
            shuffledWords[wordindex].translation, randomFromList())
        randomElements2 = randomElements2.shuffled()
        binding.checkBox8.text = randomElements2[0]
        randomElements2 = randomElements2.drop(1)
        binding.checkBox6.text = randomElements2[0]
        randomElements2 = randomElements2.drop(1)
        binding.checkBox7.text = randomElements2[0]
        randomElements2 =  randomElements2.drop(1)
        binding.checkBox9.text = randomElements2[0]
        randomElements2 = randomElements2.drop(1)
        if (wordindex == shuffledWords.lastIndex) {
            binding.button10.text = "Back"
        }
    }
    // Show next word

    fun nextWord(){
        wordindex++
        if (wordindex == shuffledWords.lastIndex+1) {

            val args = ExerciseFragmentArgs.fromBundle(requireArguments())

            val viewModel: SettingsViewModel by viewModels()
            viewModel.saveProgress(args.progress+1)
            val action =
                ExerciseFragmentDirections.actionExerciseFragmentToMainScreenFragment(args.progress+1)
            findNavController().navigate(action)
        }
        else {
            binding.checkBox6.isChecked = false;
            binding.checkBox7.isChecked = false;
            binding.checkBox8.isChecked = false;
            binding.checkBox9.isChecked = false;
            binding.button10.isVisible = false
            binding.checkBox6.isClickable = true
            binding.checkBox7.isClickable = true
            binding.checkBox8.isClickable = true
            binding.checkBox9.isClickable = true
            showWord()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setCheckedChangedListener() {
        binding.checkBox6.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
              //  binding.checkBox6.text="Wrong!"
                if(binding.checkBox6.text.equals(shuffledWords[wordindex].translation)){
                    binding.checkBox6.setTextColor(Color.GREEN);
                    binding.progressBar.progress = (wordindex+1)*100/(shuffledWords.lastIndex+1)
                    binding.button10.isVisible = true
                    binding.checkBox6.isClickable = false
                    binding.checkBox7.isClickable = false
                    binding.checkBox8.isClickable = false
                    binding.checkBox9.isClickable = false
                }
                else
                    binding.checkBox6.setTextColor(Color.RED);
                    binding.checkBox6.isClickable = false
            }
            else{
                    binding.checkBox6.setTextColor(Color.BLACK);
            }
        }
        binding.checkBox7.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if(binding.checkBox7.text.equals(shuffledWords[wordindex].translation)){
                    binding.checkBox7.setTextColor(Color.GREEN);
                    binding.progressBar.progress = (wordindex+1)*100/(shuffledWords.lastIndex+1)
                    binding.button10.isVisible = true
                    binding.checkBox6.isClickable = false
                    binding.checkBox7.isClickable = false
                    binding.checkBox8.isClickable = false
                    binding.checkBox9.isClickable = false
                }
                else
                    binding.checkBox7.setTextColor(Color.RED);
                    binding.checkBox7.isClickable = false
            }
            else{
                binding.checkBox7.setTextColor(Color.BLACK);
            }
        }
        binding.checkBox8.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if(binding.checkBox8.text.equals(shuffledWords[wordindex].translation)){
                    binding.checkBox8.setTextColor(Color.GREEN);
                    binding.progressBar.progress = (wordindex+1)*100/(shuffledWords.lastIndex+1)
                    binding.button10.isVisible = true
                    binding.checkBox6.isClickable = false
                    binding.checkBox7.isClickable = false
                    binding.checkBox8.isClickable = false
                    binding.checkBox9.isClickable = false
                }
                else
                    binding.checkBox8.setTextColor(Color.RED);
                    binding.checkBox8.isClickable = false
            }
            else{
                binding.checkBox8.setTextColor(Color.BLACK);
            }
        }
        binding.checkBox9.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if(binding.checkBox9.text.equals(shuffledWords[wordindex].translation)){
                    binding.checkBox9.setTextColor(Color.GREEN);
                    binding.progressBar.progress = (wordindex+1)*100/(shuffledWords.lastIndex+1)
                    binding.button10.isVisible = true
                    binding.checkBox6.isClickable = false
                    binding.checkBox7.isClickable = false
                    binding.checkBox8.isClickable = false
                    binding.checkBox9.isClickable = false
                }
                else
                    binding.checkBox9.setTextColor(Color.RED);
                    binding.checkBox9.isClickable = false
            }
            else{
                binding.checkBox9.setTextColor(Color.BLACK);
            }
        }
    }

}