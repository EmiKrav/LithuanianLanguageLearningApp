package edu.ktu.lithuanianlearningapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.ktu.lithuanianlearningapp.databinding.ItemWorddbBinding
import edu.ktu.lithuanianlearningapp.models.WordDB

class WordsAdapter(private val clickListener: WordDBClickListener) :
        ListAdapter<WordDB, WordsAdapter.ViewHolder>(WordDBDiffCallback()) {

    class ViewHolder(private val binding: ItemWorddbBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(wordDB: WordDB, clickListener: WordDBClickListener) {
            binding.wordDB = wordDB
            binding.clickListener = clickListener
        }
    }
    class WordDBDiffCallback : DiffUtil.ItemCallback<WordDB>() {
        override fun areItemsTheSame(oldItem: WordDB, newItem: WordDB): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WordDB, newItem: WordDB): Boolean {
            return oldItem == newItem
        }

    }
    class WordDBClickListener(private val clickListener: (wordDB: WordDB) -> Unit) {
        fun onClick(wordDB: WordDB) = clickListener(wordDB)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWorddbBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}