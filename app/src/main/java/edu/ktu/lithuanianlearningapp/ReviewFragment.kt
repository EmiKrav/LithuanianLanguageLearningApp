package edu.ktu.lithuanianlearningapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class ReviewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater
        .inflate(R.layout.fragment_review,
            container,
            false)

        view.findViewById<Button>(R.id.button13)
            .setOnClickListener {
                view.findNavController()
                    .navigate(R.id.action_reviewFragment_to_alphabetFragment)
            }
        view.findViewById<Button>(R.id.button12)
            .setOnClickListener {
                view.findNavController()
                    .navigate(R.id.action_reviewFragment_to_wordListFireBaseFragment)
            }
        return view
    }
}