package edu.ktu.lithuanianlearningapp.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddWordViewModel : ViewModel() {
    fun addWord(word_in_Lithuanian: String, translation: String, word_field: String) {
        FirebaseAuth.getInstance().currentUser?.let {user ->
            Firebase.firestore.collection("words").add(mapOf(
                "word_in_Lithuanian" to word_in_Lithuanian,
                "translation" to translation,
                "word_field" to word_field,
                "owner" to user.uid
            ))
        }
    }

}