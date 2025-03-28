package com.example.coinvertercalculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coinvertercalculator.app.User


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val Username = findViewById<EditText>(R.id.Username)
        val Password = findViewById<EditText>(R.id.Password)
        val Email = findViewById<EditText>(R.id.Email)

        Username.setText((application as User).username)
        Password.setText((application as User).password)
        Email.setText((application as User).email)

        val pf_settings = findViewById<Button>(R.id.pf_settings)
        pf_settings.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java )
            Log.e("Settings", "Settings, returned!")

            Toast.makeText(this, "Settings, returned!!!", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

        val save = findViewById<Button>(R.id.save)
        save.setOnClickListener {
            (application as User).username = Username.text.toString()
            (application as User).password = Password.text.toString()
            (application as User).email = Email.text.toString()
            Toast.makeText(this, "Saved edits", Toast.LENGTH_LONG).show()
        }
        val logout = findViewById<Button>(R.id.logout)
        logout.setOnClickListener {
            val intent = Intent(this, LogoutActivity::class.java )
            startActivity(intent)
        }
    }
}