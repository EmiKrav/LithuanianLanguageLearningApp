package edu.ktu.lithuanianlearningapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.ktu.lithuanianlearningapp.database.WordDbDatabase
import edu.ktu.lithuanianlearningapp.models.WordDB

class WordDBViewModelFactory(private val db: WordDbDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordDBViewModel::class.java)) {
            return WordDBViewModel(db) as T
        }
        throw IllegalArgumentException()
    }
}