package edu.ktu.lithuanianlearningapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.ktu.lithuanianlearningapp.databinding.FragmentWordListFireBaseBinding
import edu.ktu.lithuanianlearningapp.uiadapters.WordAdapterFireBase
import edu.ktu.lithuanianlearningapp.uiadapters.WordAdminAdapterFireBase
import edu.ktu.lithuanianlearningapp.viewmodels.WordExerciseFireBasedViewModel
import edu.ktu.lithuanianlearningapp.viewmodels.WordListFireBasedViewModel

class WordListFireBaseFragment : Fragment() {

    val viewModel: WordListFireBasedViewModel by viewModels()
    val viewModel2: WordExerciseFireBasedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentWordListFireBaseBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = WordAdapterFireBase(WordAdapterFireBase.OnDeleteClickListener { word ->
            viewModel.deleteWord(word)
        })

        val adapter2 = WordAdminAdapterFireBase()


        binding.wordRecyclerView.adapter = adapter
        binding.wordRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.adapter = adapter2
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.words.observe(viewLifecycleOwner) {
           // Toast.makeText( context,"Word: ${it.lastIndex.toString()}", Toast.LENGTH_LONG).show();
            adapter.submitList(it)
        //    Toast.makeText( context,"Word: ${adapter.currentList[1].word_in_Lithuanian}", Toast.LENGTH_LONG).show();
        }
       viewModel2.words2.observe(viewLifecycleOwner) {
            // Toast.makeText( context,"Word: ${it.lastIndex.toString()}", Toast.LENGTH_LONG).show();
            adapter2.submitList(it)
            //   Toast.makeText( context,"Word: ${adapter2.currentList[1].word_in_Lithuanian}", Toast.LENGTH_LONG).show();
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}