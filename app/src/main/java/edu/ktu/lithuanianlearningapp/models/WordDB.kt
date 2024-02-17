package edu.ktu.lithuanianlearningapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wordsDB")
data class WordDB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val word_in_Lithuanian: String,
    val translation: String,
    val word_field: String
    )