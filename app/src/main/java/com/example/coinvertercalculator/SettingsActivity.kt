package com.example.coinvertercalculator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.example.coinvertercalculator.data.Settings
import com.example.coinvertercalculator.helper.CustomListAdapterSettings


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val button_back = findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener(){
            finish()
        }

//        val listView =findViewById<ListView>(R.id.listView)

        val settings = listOf(
            Settings("Profile", R.drawable.person_24px),
            Settings("About Us", R.drawable.group_24px)
        )

        val adapter = CustomListAdapterSettings(
            this, settings,
            onClick = {setting, position ->
                when(position) {
                    0 -> {
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                    }
                    1 -> {
                        val intent = Intent(this, DeveloperActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        )
//        listView.adapter = adapter


        val user_profile = findViewById<Button>(R.id.user_profile)
        user_profile.setOnClickListener(){
            Log.e("User Profile",  "User Profile, clicked!")


            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val about_us = findViewById<Button>(R.id.about_us)
        about_us.setOnClickListener{
            Log.e("About Us", "About us, clicked!")


            val intent = Intent(this, DeveloperActivity::class.java)
            startActivity(intent)
        }

        val toggleDarkMode = findViewById<SwitchCompat>(R.id.toggleDarkMode)
        val sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val nightMode = sharedPreferences.getBoolean("night", false)


        toggleDarkMode.isChecked = nightMode


        toggleDarkMode.setOnCheckedChangeListener { _ , isChecked ->
            if(!isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("night", false)
                editor.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("night", true)
                editor.apply()
            }
//            recreate()
        }


    }
}
