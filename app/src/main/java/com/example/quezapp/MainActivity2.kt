package com.example.flashcardquizapp


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quezapp.ScoreActivity

class FlashcardActivity : AppCompatActivity() {
    private val questions = arrayOf<String?>(
        "Nelson Mandela was the president in 1994",
        "The Eiffel Tower is in Berlin",
        "World War II ended in 1945",
        "The Great Wall is in Japan",
        "The Declaration of Independence was signed in 1776"
    )
    private val answers = booleanArrayOf(true, false, true, false, true)

    private var currentIndex = 0
    private var score = 0

    private var questionTextView : TextView? = null
    private var trueButton : Button? = null
    private var falseButton : Button? = null
    private var feedbackTextView : TextView? = null
    private var nextButton : Button? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        questionTextView = findViewById<TextView?>(R.id.questionTextView)
        trueButton = findViewById<Button?>(R.id.trueButton)
        falseButton = findViewById<Button?>(R.id.falseButton)
        feedbackTextView = findViewById<TextView?>(R.id.feedbackTextView)
        nextButton = findViewById<Button?>(R.id.nextButton)

        showQuestion()

        trueButton!!.setOnClickListener(View.OnClickListener { view : View? ->  // Use lambda expressions
            checkAnswer(true)
        })

        falseButton!!.setOnClickListener(View.OnClickListener { view : View? ->
            checkAnswer(false)
        })

        nextButton!!.setOnClickListener(View.OnClickListener { view : View? ->
            currentIndex++
            if (currentIndex < questions.size) {
                showQuestion()
                feedbackTextView!!.setText("")
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", questions.size)
                intent.putExtra("QUESTIONS", questions)
                intent.putExtra("ANSWERS", answers)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun showQuestion() {
        questionTextView!!.setText(questions[currentIndex])
    }

    private fun checkAnswer(userAnswer : Boolean) {
        if (userAnswer == answers[currentIndex]) {
            feedbackTextView!!.setText("Correct!")
            score++
        } else {
            feedbackTextView!!.setText("Incorrect")
        }
    }
}