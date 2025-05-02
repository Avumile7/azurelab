package com.example.flashcardquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.id.layout.activity_main)

        val startButton: Button = findViewById(R.id.button)
        startButton.setOnClickListener {
            val FlashcardActivity = null
            val intent = Intent(this, FlashcardActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}
