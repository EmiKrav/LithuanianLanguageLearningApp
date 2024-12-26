package edu.ktu.lithuanianlearningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.ktu.lithuanianlearningapp.databinding.FragmentExercise2EndBinding

class Exercise2EndFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExercise2EndBinding.inflate(inflater)
        val args = Exercise2EndFragmentArgs.fromBundle(requireArguments())
        if (args.win) {
            binding.outcomeText.text = resources.getString(R.string.The_end)
            binding.summaryText.text =
                resources.getString(
                    R.string.summary_win,
                    args.symbol,
                    args.mistakes
                )
        }
        binding.playAgainBtn.setOnClickListener {
            val action = Exercise2EndFragmentDirections.actionExercise2EndFragmentToExercise2Fragment()
            findNavController().navigate(action)
        }
        binding.button3.setOnClickListener{
            val action = Exercise2EndFragmentDirections.actionExercise2EndFragmentToMainScreenFragment(0)
            findNavController().navigate(action)

        }
        return binding.root
    }
}