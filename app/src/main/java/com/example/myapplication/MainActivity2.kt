package com.MainActivity2.kt

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // 1. Find views by their IDs from activity_main_weather.xml
        val weatherDisplayTextView: TextView = findViewById(R.id.weatherDisplayTextView)
        val averageTempTextView: TextView = findViewById(R.id.averageTempTextView)
        val backButton: Button = findViewById(R.id.backButton)

        // 2. Sample Hardcoded Data (using 2 parallel arrays as required)
        val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val maxTemps = arrayOf(25, 29, 22, 24, 20, 18, 16) // Values explicitly from the screenshot

        // 3. Display each day with its max temperature using a loop
        var displayText = "Weekly Maximum Temperatures:\n\n"
        // Use a loop to iterate through the indices to access elements from both parallel arrays
        for (i in days.indices) {
            displayText += "${days[i]}: ${maxTemps[i]}°C\n"
        }
        weatherDisplayTextView.text = displayText

        // 4. Calculate and show the average max temperature using a loop
        var sumOfTemps = 0
        // Use a loop to sum up the temperatures
        for (temp in maxTemps) {
            sumOfTemps += temp
        }
        // Calculate average, ensuring to handle the case of an empty array to prevent division by zero
        val averageTemp = if (maxTemps.isNotEmpty()) sumOfTemps.toDouble() / maxTemps.size else 0.0
        // Format the average to two decimal places
        averageTempTextView.text = String.format("Average Max Temperature: %.2f°C", averageTemp)

        // 5. Include a Back button to return to the Welcome screen
        backButton.setOnClickListener {
            finish() // This method finishes the current activity and returns to the previous one on the back stack.
        }
    }
}