package com.example.coinvertercalculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class DeveloperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        val dev_settings = findViewById<Button>(R.id.dev_settings)
        dev_settings.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java )
            Log.e("Settings", "Settings, returned!")

            Toast.makeText(this, "Settings, returned!!!", Toast.LENGTH_LONG).show()
            startActivity(intent)

        }

    }
}