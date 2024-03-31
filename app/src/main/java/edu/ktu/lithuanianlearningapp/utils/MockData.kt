package edu.ktu.lithuanianlearningapp.utils

import edu.ktu.lithuanianlearningapp.Word
import edu.ktu.lithuanianlearningapp.models.WordDB
import kotlin.random.Random

object MockData {
    val wordDB = listOf(
        WordDB(
            0,"Vienas", "Один", "цифры"
        ),
        WordDB(
            0,"Du", "Два", "цифры"
        ),
        WordDB(
            0,"Trys", "Три", "цифры"
        )
    )

    fun getRandomWord(): WordDB = wordDB[Random.nextInt(0, wordDB.size)]
}