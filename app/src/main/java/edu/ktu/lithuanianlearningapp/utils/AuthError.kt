package edu.ktu.lithuanianlearningapp.utils

enum class AuthError
{
    NoError,
    NoEmailProvided,
    MalformedEmail,
    NoPasswordProvided,
    WrongCredentials,
    UserExists,
    WeakPassword,
}