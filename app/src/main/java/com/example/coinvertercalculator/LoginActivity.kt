package com.example.coinvertercalculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coinvertercalculator.app.User

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var edittext_username = findViewById<EditText>(R.id.edittext_username)
        var edittext_password = findViewById<EditText>(R.id.edittext_password)

        intent?.let {
            it.getStringExtra("username")?.let { username->
                edittext_username.setText(username)
            }
            it.getStringExtra("password")?.let { password->
                edittext_password.setText(password)
            }
        }
        val button_login = findViewById<Button>(R.id.button_login)
        button_login.setOnClickListener {
            val username = edittext_username.text.toString()
            val password = edittext_password.text.toString()

            if(username.isNullOrEmpty() || password.isNullOrEmpty()){
                Toast.makeText(this, "Username and Password cannot be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if((application as User).username.isNullOrEmpty()||(application as User).password.isNullOrEmpty()){
                Toast.makeText(this, "No registered user, please sign up", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
        }

        val button_signup = findViewById<Button>(R.id.button_signup)
        button_signup.setOnClickListener {
            Log.e("Android", "Button Clicked")

            Toast.makeText(this, "Button is clicked!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}