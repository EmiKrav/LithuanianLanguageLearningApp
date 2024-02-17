package edu.ktu.lithuanianlearningapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class Learn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_learn)
        val wordList = listOf(
            Word("Vienas", "One", "numbers"),
            Word("Du", "Two", "numbers"),
            Word("Trys", "Three", "numbers"),
            Word("Ne", "No", "Main"),
            Word("Taip", "Yes", "Main"),
            Word("Gal", "Maybe", "Conversation"),
            Word("Ačiū", "Thank you", "Main"),
            Word("Prašom", "Welcome", "Main"),
            Word("Keturi", "Four", "numbers"),
            Word("Padėkite man", "Help me", "Main"),
            Word("Pagalba", "Help (n)", "Main"),
            Word("Vardas", "Name", "About you"),
            Word("Pavardė", "Surname", "About you"),
        )
        val recycler = findViewById(R.id.word_list2) as RecyclerView
        recycler.adapter = WordAdapter(wordList)
    }

}