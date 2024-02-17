package edu.ktu.lithuanianlearningapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class WordAdapter (val words: List<Word>):
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordinLithuanianText = view.findViewById(R.id.word_in_Lithuanian_text) as TextView
        val translationText = view.findViewById(R.id.translation_text) as TextView
        val wordfieldText = view.findViewById(R.id.word_field_text) as TextView
    }
    override fun getItemCount(): Int {
        return words.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_word, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = words[position]
        holder.wordinLithuanianText.text = data.word_in_Lithuanian
        holder.translationText.text = data.translation
        holder.wordfieldText.text = data.word_field
    }
}

