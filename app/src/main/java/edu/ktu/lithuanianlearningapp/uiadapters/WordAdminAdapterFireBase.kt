package edu.ktu.lithuanianlearningapp.uiadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.ktu.lithuanianlearningapp.databinding.FragmentExerciseBinding
import edu.ktu.lithuanianlearningapp.databinding.ItemWordFirebaseAdminBinding
import edu.ktu.lithuanianlearningapp.databinding.ItemWordFirebaseBinding
import edu.ktu.lithuanianlearningapp.models2.Word

class WordAdminAdapterFireBase() :
    ListAdapter<Word, WordAdminAdapterFireBase.ViewHolder>(DogDiff()) {

    class ViewHolder(val binding: ItemWordFirebaseAdminBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word2: Word) {
            binding.word2 = word2
        }
    }


    class DogDiff : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWordFirebaseAdminBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}