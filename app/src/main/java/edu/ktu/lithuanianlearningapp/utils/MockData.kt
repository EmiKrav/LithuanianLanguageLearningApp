package edu.ktu.lithuanianlearningapp.utils

import edu.ktu.lithuanianlearningapp.Word
import edu.ktu.lithuanianlearningapp.models.WordDB
import kotlin.random.Random

object MockData {
    val wordDB = listOf(
        WordDB(
            0,"Vienas", "One", "numbers"
        ),
        WordDB(
            0,"Du", "Two", "numbers"
        ),
        WordDB(
            0,"Trys", "Three", "numbers"
        )
    )

    fun getRandomWord(): WordDB = wordDB[Random.nextInt(0, wordDB.size)]
}