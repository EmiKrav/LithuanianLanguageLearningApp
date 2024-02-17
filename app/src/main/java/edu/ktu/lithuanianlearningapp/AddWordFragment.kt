package edu.ktu.lithuanianlearningapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.ktu.lithuanianlearningapp.databinding.FragmentAddWordFirebaseBinding
import edu.ktu.lithuanianlearningapp.viewmodels.AddWordViewModel

class AddWordFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAddWordFirebaseBinding.inflate(inflater, container, false)
        val viewModel: AddWordViewModel by viewModels()
        binding.lifecycleOwner = viewLifecycleOwner

        binding.addBtn.setOnClickListener {
            viewModel.addWord(
                binding.nameTextInput.text.toString(),
                binding.ageTextInput.text.toString(),
                binding.breedTextInput.text.toString()
            )
            findNavController().popBackStack()
        }

        return binding.root
    }
}