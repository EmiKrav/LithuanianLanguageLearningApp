package edu.ktu.lithuanianlearningapp.uiadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.ktu.lithuanianlearningapp.databinding.ItemWordFirebaseBinding
import edu.ktu.lithuanianlearningapp.models2.WordUser

class WordAdapterFireBase(private val onDeleteClickListener: OnDeleteClickListener) :
    ListAdapter<WordUser, WordAdapterFireBase.ViewHolder>(DogDiff()) {

    class ViewHolder(val binding: ItemWordFirebaseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: WordUser, onDeleteClickListener: OnDeleteClickListener) {
            binding.word = word
            binding.onDeleteClickListener = onDeleteClickListener
        }
    }


    class DogDiff : DiffUtil.ItemCallback<WordUser>() {
        override fun areItemsTheSame(oldItem: WordUser, newItem: WordUser): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: WordUser, newItem: WordUser): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWordFirebaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onDeleteClickListener)
    }

    class OnDeleteClickListener(val clickListener: (word: WordUser) -> Unit) {
        fun onClick(word: WordUser) = clickListener(word)
    }

}