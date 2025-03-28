package com.example.coinvertercalculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val button_settings = findViewById<Button>(R.id.button_settings)
        button_settings.setOnClickListener {
            Toast.makeText(this, "Redirects to settings", Toast.LENGTH_LONG).show()
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val button_profile = findViewById<Button>(R.id.button_profile)
        button_profile.setOnClickListener {
            Toast.makeText(this, "Redirects to profile", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val button_convert = findViewById<Button>(R.id.button_convert)
        button_convert.setOnClickListener {
            Toast.makeText(this, "Redirects to Coinverter Calculator", Toast.LENGTH_LONG).show()
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }

        val button_history = findViewById<Button>(R.id.button_history)
        button_history.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }


    }
}