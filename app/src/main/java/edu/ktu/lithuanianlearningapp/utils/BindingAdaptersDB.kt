package edu.ktu.lithuanianlearningapp.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.ktu.lithuanianlearningapp.adapters.WordsAdapter
import edu.ktu.lithuanianlearningapp.models.WordDB

@BindingAdapter("wordsDB")
fun RecyclerView.setWordsDB(wordsDB: List<WordDB>?) {
    wordsDB?.let { (adapter as WordsAdapter).submitList(wordsDB) }
}