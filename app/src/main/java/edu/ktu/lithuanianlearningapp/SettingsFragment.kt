package edu.ktu.lithuanianlearningapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.ktx.Firebase
import edu.ktu.lithuanianlearningapp.databinding.FragmentSettingsBinding
import edu.ktu.lithuanianlearningapp.viewmodels.SettingsViewModel

class SettingsFragment : Fragment() {
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var wordListenerRegistration: ListenerRegistration? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)
        val viewModel: SettingsViewModel by viewModels()
     //  binding.lifecycleOwner = viewLifecycleOwner

        val chk = binding.checkBox;
        val chk2 = binding.checkBox3;
        val chk3 = binding.checkBox5;
        val b15 =  binding.button15;
        val b7 =  binding.button7;
        val b8 =  binding.button8;

        val args2 = SettingsFragmentArgs.fromBundle(requireArguments());

        chk.isChecked = args2.C1;
        chk2.isChecked = args2.C2;
        chk3.isChecked = args2.C3;

       // mainVm.saveNameUser("Prateek")
//        viewModel.getUserName.observe(viewLifecycleOwner){
//            chk.isChecked = it
//
//        }
        chk.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                   var Button1 = true;

                    viewModel.saveArg1(true)
                    val action =
                        SettingsFragmentDirections.actionSettingsFragmentToMainScreenFragment(0,Button1, args2.C3, args2.C2)
                    findNavController().navigate(action)
                }
                else{
                      var Button1 = false;
                        viewModel.saveArg1(false)
                        val action = SettingsFragmentDirections
                            .actionSettingsFragmentToMainScreenFragment(0,Button1, args2.C3, args2.C2)
                            findNavController().navigate(action)
                }

            }
        chk3.setOnCheckedChangeListener { buttonView2, isChecked3 ->
            if (isChecked3) {
                var   Button3 = true;

                viewModel.saveArg3(true)
                val action2 = SettingsFragmentDirections
                    .actionSettingsFragmentToMainScreenFragment(0,args2.C1, Button3, args2.C2)
                findNavController().navigate(action2)
            }
            else {
                var Button3 = false;

                viewModel.saveArg3(false)
                val action2 = SettingsFragmentDirections
                    .actionSettingsFragmentToMainScreenFragment(0,args2.C1,Button3, args2.C2)
                findNavController().navigate(action2)
            }

        }
        chk2.setOnCheckedChangeListener { buttonView3, isChecked2 ->
            if (isChecked2) {
               var Button2 = true;
                viewModel.saveArg2(true)
                val action = SettingsFragmentDirections
                    .actionSettingsFragmentToMainScreenFragment(0,args2.C1, args2.C3, Button2)
                findNavController().navigate(action)
            }
            else{
              var  Button2 = false;
                viewModel.saveArg2(false)
                val action = SettingsFragmentDirections
                    .actionSettingsFragmentToMainScreenFragment(0,args2.C1, args2.C3, Button2)
                findNavController().navigate(action)
            }

        }
        b15.setOnClickListener {
            currentUser?.let {
                wordListenerRegistration?.remove()
                FirebaseAuth.getInstance().signOut()
            }
            findNavController().navigate(R.id.action_settingsFragment_to_signInFragment)
        }
        b7.setOnClickListener {
            viewModel.saveArg1(false)
            viewModel.saveArg2(false)
            viewModel.saveArg3(false)
            viewModel.saveProgress(0)
            val action = SettingsFragmentDirections
                .actionSettingsFragmentToMainScreenFragment(0,false,false, false)
            findNavController().navigate(action)
        }
        b8.setOnClickListener {

            val user = Firebase.auth.currentUser!!

            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User account deleted.")
                        Toast.makeText( context,"Successfully Deleted User", Toast.LENGTH_LONG).show();
                        val action = SettingsFragmentDirections
                            .actionSettingsFragmentToSignInFragment()
                        findNavController().navigate(action)
                    }
                }
        }

        return binding.root
    }
}