package edu.ktu.lithuanianlearningapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.ktu.lithuanianlearningapp.models2.WordUser

class WordListFireBasedViewModel : ViewModel() {

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var wordListenerRegistration: ListenerRegistration? = null

    private val _words = MutableLiveData<List<WordUser>>()
    val words: LiveData<List<WordUser>> = _words

    init {
        currentUser?.let {
            wordListenerRegistration = Firebase.firestore.collection("words").whereEqualTo("owner", currentUser.uid)
                .addSnapshotListener { snapshot, e ->
                    e?.let {
                        Log.w("TAG", "listen:error", e)
                        return@addSnapshotListener
                    }

                    if (snapshot != null && !snapshot.isEmpty) {
                        _words.value = snapshot.documents.map {
                            WordUser(
                                it.id, it["word_in_Lithuanian"] as String,
                                it["translation"] as String,
                                it["word_field"] as String
                            )
                        }
                    }
                }
        }
    }

    fun deleteWord(word: WordUser)
    {
        currentUser?.let {
            Firebase.firestore.collection("words").document(word.uid).delete()
        }
    }
}