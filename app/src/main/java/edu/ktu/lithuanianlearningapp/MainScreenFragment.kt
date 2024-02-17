package edu.ktu.lithuanianlearningapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import edu.ktu.lithuanianlearningapp.databinding.FragmentMainScreenBinding
import edu.ktu.lithuanianlearningapp.viewmodels.SettingsViewModel

class MainScreenFragment : Fragment() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainScreenBinding.inflate(inflater)
        val viewModel: SettingsViewModel by viewModels()
        // binding.lifecycleOwner = viewLifecycleOwner

        val args = MainScreenFragmentArgs.fromBundle(requireArguments())
        ///////////////////////////////////////////////////////////////////
        val b1 = binding.button4;
        val b2 = binding.imageButton3;
        val b3 = binding.button;
        var tv = binding.textView5;
        var Button1 = args.Button1;
        var Button2 = args.Button2;
        var Button3 = args.Button3;
        val prog = args.progress;
        var sk = args.progress;

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

        }

        viewModel.getArg1.observe(viewLifecycleOwner){
            Button1 =  it;
            b1.isVisible =  !Button1
        }
        viewModel.getArg2.observe(viewLifecycleOwner){
            Button2 =  it;
            b2.isVisible =  Button2
        }
        viewModel.getArg3.observe(viewLifecycleOwner){
            Button3 =  it;
            b3.isVisible =  !Button3
        }

        if(prog > 0 && sk < 12){
            var sk2 = sk;
            tv.text = "Done: $sk2/12";
            sk = sk2;
            viewModel.saveProgress(sk)
        }

        viewModel.getProgress.observe(viewLifecycleOwner) {
            if (it <= 12) {
                tv.text = "Done: $it/10";
            }
        }

                if(Button1){
                       // b1.visibility =  View.INVISIBLE;

                }
        else{
            b1.setOnClickListener {

                val action = MainScreenFragmentDirections
                    .actionMainScreenFragmentToExerciseFragment(sk)
                findNavController()
                    .navigate(action)
                }
        }
        ///////////////////////////////////////////////////////////////////////

             if(!Button2){
                b2.visibility = View.INVISIBLE
             }

            b2.setOnClickListener {
                   findNavController()
                        .navigate(R.id.action_mainScreenFragment_to_addWordFragment)
                }

        ////////////////////////////////////////////////////////////////////////
        if(Button3){
                b3.visibility = View.INVISIBLE
        }
       else{
           b3.setOnClickListener {
                    findNavController()
                        .navigate(R.id.action_mainScreenFragment_to_exercise2Fragment)
                }
        }
        ///////////////////////////////////////////////////
        binding.button5
            .setOnClickListener {
              findNavController()
                    .navigate(R.id.action_mainScreenFragment_to_reviewFragment)
            }
        binding.button6
            .setOnClickListener {
                findNavController()
                    .navigate(R.id.action_mainScreenFragment_to_learnFragment)
            }
        binding.imageButton2
            .setOnClickListener {
               val action = MainScreenFragmentDirections
                   .actionMainScreenFragmentToSettingsFragment(Button1, Button2, Button3)
               findNavController()
                    .navigate(action)
            }
        return binding.root
    }

}