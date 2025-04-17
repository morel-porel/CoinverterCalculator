package com.example.coinvertercalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coinvertercalculator.app.User
import com.example.coinvertercalculator.helper.UserPreferenceManager

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val edittext_username = findViewById<EditText>(R.id.edittext_username)
        val edittext_email = findViewById<EditText>(R.id.edittext_email)
        val edittext_password = findViewById<EditText>(R.id.edittext_password)
        val edittext_confirmpassword = findViewById<EditText>(R.id.edittext_confirmpassword)

        val button_signup = findViewById<Button>(R.id.button_signup)
        button_signup.setOnClickListener {
            val username = edittext_username.text.toString()
            val email = edittext_email.text.toString()
            val password = edittext_password.text.toString()
            val confirmpassword = edittext_confirmpassword.text.toString()
            if(!email.contains("@")){
                Toast.makeText(this, "Not a valid email address", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(username.isNullOrEmpty()||password.isNullOrEmpty()||confirmpassword.isNullOrEmpty()){
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(password != confirmpassword){
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //save to device
            val userPrefsManager = UserPreferenceManager(this)

            if(!userPrefsManager.userExists(username)){

                (application as User).username = username
                (application as User).email = email
                (application as User).password = password

                userPrefsManager.addOrEditUser(username, email, password, true)

                val intent = Intent(this, LoginActivity::class.java).apply{
                    putExtra("username", username)
                    putExtra("password", password)
                    putExtra("email", email)
                }
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Username is taken", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

        }
    }
}