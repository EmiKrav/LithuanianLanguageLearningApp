package edu.ktu.lithuanianlearningapp.viewmodels

import androidx.lifecycle.*
import edu.ktu.lithuanianlearningapp.database.WordDbDatabase
import edu.ktu.lithuanianlearningapp.models.WordDB
import edu.ktu.lithuanianlearningapp.utils.MockData
import kotlinx.coroutines.launch

class WordDBViewModel(private val db: WordDbDatabase) : ViewModel() {

    private val _wordsDB = db.WordDBDao().getAllWords().asLiveData()
    val wordsDB: LiveData<List<WordDB>>
        get() = _wordsDB

    fun addWordDB() {
        viewModelScope.launch {
            db.WordDBDao().insertAll(MockData.getRandomWord())
        }
    }
    fun deleteWordDB(wordDB: WordDB) {
        viewModelScope.launch {
            db.WordDBDao().delete(wordDB)
        }
    }

}