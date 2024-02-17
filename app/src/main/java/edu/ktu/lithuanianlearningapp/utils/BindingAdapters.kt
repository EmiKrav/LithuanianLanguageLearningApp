package edu.ktu.lithuanianlearningapp.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import edu.ktu.lithuanianlearningapp.R

@BindingAdapter("errorText")
fun TextInputLayout.setErrorText(errorText: String?) {
    error = errorText
}