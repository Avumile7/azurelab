package com.example.flashcardquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FlashcardActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994",
        "The Eiffel Tower is in Berlin",
        "World War II ended in 1945",
        "The Great Wall is in Japan",
        "The Declaration of Independence was signed in 1776"
    )
    private val answers = booleanArrayOf(true, false, true, false, true)

    private var currentIndex = 0
    private var score = 0

    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var feedbackTextView: TextView
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        questionTextView = findViewById(R.id.questionTextView)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        feedbackTextView = findViewById(R.id.feedbackTextView)
        nextButton = findViewById(R.id.nextButton)

        showQuestion()

        trueButton.setOnClickListener {  // Use lambda expressions
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                showQuestion()
                feedbackTextView.text = ""
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", questions.size)
                intent.putExtra("QUESTIONS", questions)
                intent.putExtra("ANSWERS", answers)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showQuestion() {
        questionTextView.text = questions[currentIndex]
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[currentIndex]) {
            feedbackTextView.text = "Correct!"
            score++
        } else {
            feedbackTextView.text = "Incorrect"
        }
    }
}
