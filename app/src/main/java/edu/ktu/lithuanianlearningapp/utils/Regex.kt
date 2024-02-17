package edu.ktu.lithuanianlearningapp.utils

import java.util.regex.Pattern

    val PASSWORD: Pattern
        get() = Pattern.compile(
            "(?=(.*[A-Z])+)" +
                    "(?=(.*[A-Z])+)" +
                    "(?=(.*[!@#\$%^&*()\\-__+.\\d])+)" +
                    ".{8,}"
        )