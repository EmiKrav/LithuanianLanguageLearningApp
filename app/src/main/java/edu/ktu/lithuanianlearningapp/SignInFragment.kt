package edu.ktu.lithuanianlearningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import edu.ktu.lithuanianlearningapp.databinding.FragmentSignInBinding
import edu.ktu.lithuanianlearningapp.viewmodels.SignInViewModel2

class SignInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignInBinding.inflate(inflater, container, false)
        val viewModel2: SignInViewModel2 by viewModels()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel2

        viewModel2.currentUser.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainScreenFragment(0))
            }
        }

        binding.button9.setOnClickListener {
            viewModel2.signIn(
                binding.UsertextInputEditText.text.toString(),
                binding.PasswordtextInputEditText2.text.toString()
            )
        }
        binding.button2.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToRegisterFragment())
        }

        return binding.root
    }
}