package edu.ktu.dogsfirebase.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("authEmailError")
fun TextInputLayout.setAuthEmailError(authError: AuthError)
{
    error = when(authError){
        AuthError.NoEmailProvided -> "Электронная почта не указанаd"
        AuthError.MalformedEmail -> "Электронная почта недействительна"
        AuthError.UserExists -> "Пользователь с таким адресом электронной почты уже существует"
        else -> null
    }
}

@BindingAdapter("authPasswordError")
fun TextInputLayout.setAuthPasswordError(authError: AuthError)
{
    error = when(authError){
        AuthError.NoPasswordProvided-> "Пароль не указан"
        AuthError.WrongCredentials-> "Email или пароль указан неверно"
        AuthError.WeakPassword-> "Пароль должен иметь длину не менее 8 символов, содержать одну строчную," +
                " одну заглавную букву и специальный символ (!@#\$%^&*()\\-_+.) или цифру"
        else->null
    }
}