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
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val button_profile = findViewById<Button>(R.id.button_profile)
        button_profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val button_convert = findViewById<Button>(R.id.button_convert)
        button_convert.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }

        val button_history = findViewById<Button>(R.id.button_history)
        button_history.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        val button_rates = findViewById<Button>(R.id.button_rates)
        button_rates.setOnClickListener {
            val intent = Intent(this, RatesActivity::class.java)
            startActivity(intent)
        }


    }
}