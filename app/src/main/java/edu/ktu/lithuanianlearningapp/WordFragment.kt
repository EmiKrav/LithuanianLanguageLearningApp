package edu.ktu.lithuanianlearningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import edu.ktu.lithuanianlearningapp.adapters.WordsAdapter
import edu.ktu.lithuanianlearningapp.database.WordDbDatabase
import edu.ktu.lithuanianlearningapp.databinding.FragmentWordBinding
import edu.ktu.lithuanianlearningapp.viewmodels.WordDBViewModel
import edu.ktu.lithuanianlearningapp.viewmodels.WordDBViewModelFactory

class WordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWordBinding.inflate(inflater)

        val viewModel: WordDBViewModel by viewModels {
            WordDBViewModelFactory(
                WordDbDatabase.getInstance(
                    requireContext()
                )
            )
        }

        val adapter = WordsAdapter(WordsAdapter.WordDBClickListener{
            viewModel.deleteWordDB(it)
        })
        binding.wordDbRv.adapter = adapter
        binding.wordDbRv.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}