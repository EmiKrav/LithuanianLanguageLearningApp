package edu.ktu.lithuanianlearningapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class WordAdapter3 (val type: Int, val title: String, val words: List<Word>):
    RecyclerView.Adapter<WordAdapter3.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      //  if(view.findViewById(R.id.word_field_text) == title)
       // {
            val wordinLithuanianText = view.findViewById(R.id.word_in_Lithuanian_text) as TextView
            val translationText = view.findViewById(R.id.translation_text) as TextView
            val wordfieldText = view.findViewById(R.id.word_field_text) as TextView
      //  }

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
        if (title != "All") {
            if(type == 1){
                if (data.word_field.equals(title)) {
                    holder.wordinLithuanianText.text = data.word_in_Lithuanian
                    holder.translationText.text = data.translation
                    holder.wordfieldText.text = data.word_field
                } else {
                    holder.wordinLithuanianText.isVisible = false;

                    holder.translationText.isVisible = false;
                    holder.wordfieldText.isVisible = false;
                }
            }
            if(type ==0) {
                if (data.word_field.equals(title)) {
                    holder.wordinLithuanianText.text = data.word_in_Lithuanian
                    holder.translationText.text = ""
                    holder.wordfieldText.text = data.word_field
                }
                else {
                    holder.wordinLithuanianText.isVisible = false;
                    holder.translationText.isVisible = false;
                    holder.wordfieldText.isVisible = false;
                }
            }
        }
        else{
            if(type == 1) {
                holder.wordinLithuanianText.text = data.word_in_Lithuanian
                holder.translationText.text = data.translation
                holder.wordfieldText.text = data.word_field
            }
            if(type == 0) {
                holder.wordinLithuanianText.text = data.word_in_Lithuanian
                holder.translationText.text = ""
                holder.wordfieldText.text = data.word_field
            }
        }
    }
}

