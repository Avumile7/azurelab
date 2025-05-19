package com.MainActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Find views by their IDs from activity_main.xml
        val appNameTextView: TextView = findViewById(R.id.appNameTextView)
        val studentInfoTextView: TextView = findViewById(R.id.studentInfoTextView)
        val logoImageView: ImageView = findViewById(R.id.logoImageView)
        val proceedButton: Button = findViewById(R.id.proceedButton)

        // 2. Show app name (e.g., "SimpleWeather") - from strings.xml
        appNameTextView.text = getString(R.string.app_name)

        // 3. Display your name and student number
        val myName = "Your Name Here" // <<< IMPORTANT: REPLACE WITH YOUR ACTUAL NAME
        val studentNumber = "Your Student Number Here" // <<< IMPORTANT: REPLACE WITH YOUR ACTUAL STUDENT NUMBER
        studentInfoTextView.text = "Developer: $myName\nStudent No: $studentNumber"

        // 4. Set a simple logo or icon
        // If you have a custom drawable named 'ic_weather', uncomment and use:
        // logoImageView.setImageResource(R.drawable.ic_weather)
        // Otherwise, it will default to @mipmap/ic_launcher_round as set in the XML.

        // 5. Set up button click listener to proceed to the Main Screen
        proceedButton.setOnClickListener {
            // Use an Intent for basic screen navigation to MainWeatherActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}