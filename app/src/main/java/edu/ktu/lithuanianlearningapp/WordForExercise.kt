package edu.ktu.lithuanianlearningapp

import android.content.ContentValues
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.currentCoroutineContext

data class WordW (
    var type: String, var translation: String,
)
