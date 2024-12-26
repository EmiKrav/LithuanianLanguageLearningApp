package edu.ktu.lithuanianlearningapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Review : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        findViewById<Button>(R.id.button13).setOnClickListener {
            val intent = Intent(this, Alphabet::class.java)
            startActivity(intent)
        }
    }
}