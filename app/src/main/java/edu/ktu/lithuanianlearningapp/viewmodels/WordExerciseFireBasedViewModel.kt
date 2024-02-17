package edu.ktu.lithuanianlearningapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.ktu.lithuanianlearningapp.models2.Word

class WordExerciseFireBasedViewModel : ViewModel() {

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var wordListenerRegistration: ListenerRegistration? = null

    private val _words2 = MutableLiveData<List<Word>>()
    val words2: LiveData<List<Word>> = _words2

    init {
        currentUser?.let {
            wordListenerRegistration = Firebase.firestore.collection("words").whereEqualTo("ShowOthers", true)
                .addSnapshotListener { snapshot, e ->
                    e?.let {
                        Log.w("TAG", "listen:error", e)
                        return@addSnapshotListener
                    }

                    if (snapshot != null && !snapshot.isEmpty) {
                        _words2?.value = snapshot.documents.map {
                            Word(
                                it.id, it["word_in_Lithuanian"] as String,
                                it["translation"] as String,
                                it["word_field"] as String,
                                it["ShowOthers"] as Boolean
                            )
                        }
                    }
                }
        }

    }
}