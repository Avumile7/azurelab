package com.example.quezapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    private lateinit var scoreTextView: TextView
    private lateinit var feedbackTextView: TextView
    private lateinit var reviewButton: Button
    private lateinit var exitButton: Button

    private lateinit var questions: Array<String>
    private lateinit var answers: BooleanArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        scoreTextView = findViewById(R.id.scoreTextView)
        feedbackTextView = findViewById(R.id.feedbackTextView)
        reviewButton = findViewById(R.id.reviewButton)
        exitButton = findViewById(R.id.exitButton)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        questions = intent.getStringArrayExtra("QUESTIONS") ?: arrayOf()
        answers = intent.getBooleanArrayExtra("ANSWERS") ?: booleanArrayOf()

        scoreTextView.text = "You scored $score out of $total"
        feedbackTextView.text = if (score >= total / 2) "Great job!" else "Keep practising!"

        reviewButton.setOnClickListener {
            val builder = StringBuilder()
            for (i in questions.indices) {
                builder.append("${i + 1}. ${questions[i]}\nAnswer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            feedbackTextView.text = builder.toString()
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}