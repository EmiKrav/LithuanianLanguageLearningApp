package edu.ktu.lithuanianlearningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import edu.ktu.lithuanianlearningapp.databinding.FragmentRegisterBinding
import edu.ktu.lithuanianlearningapp.viewmodels.SignUpViewModel2

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val viewModel2: SignUpViewModel2 by viewModels()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel2

        viewModel2.currentUser.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToSignInFragment())
            }
        }

        binding.button9.setOnClickListener {
            viewModel2.signUp(
                binding.textInputEditText.text.toString(),
                binding.textInputEditText2.text.toString()
            )
        }

        return binding.root
    }
}