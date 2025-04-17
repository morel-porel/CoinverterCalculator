package com.example.coinvertercalculator.helper

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.example.coinvertercalculator.data.ConversionResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.LinkedList
import java.util.Queue

class UserPreferenceManager(private val context: Context) {

    fun userExists(username: String): Boolean {
        val sp = context.getSharedPreferences(username, Context.MODE_PRIVATE)
        return sp.contains("username")
    }

    fun saveHistoryToDevice(context: Context, username: String, history: Queue<ConversionResult>) {
        val sharedPreferences = context.getSharedPreferences(username, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val jsonString = gson.toJson(history)
        editor.putString("history", jsonString)
        editor.commit()
    }

    fun getHistoryFromDevice(context: Context, username: String): Queue<ConversionResult> {
        val sharedPreferences = context.getSharedPreferences(username, Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString("history", null)
        if (jsonString != null) {
            val gson = Gson()
            val type = object : TypeToken<Queue<ConversionResult>>() {}.type
            return gson.fromJson(jsonString, type)
        }
        return LinkedList() // Return an empty list if no history is found
    }

    fun getUserDetails(username: String): Map<String, String?>? {
        val sp = context.getSharedPreferences(username, Context.MODE_PRIVATE)
        return if (sp.contains("username")) {
            mapOf(
                "username" to sp.getString("username", ""),
                "email" to sp.getString("email", ""),
                "password" to sp.getString("password", "")
            )
        } else {
            null
        }
    }

    fun addOrEditUser(username: String, email: String, password: String, type: Boolean) {

        val sp = context.getSharedPreferences(username, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.commit()

        if(type) Toast.makeText(context, "$username added successfully", Toast.LENGTH_LONG).show()

    }

    fun removeUser(username: String) {
        val sp = context.getSharedPreferences(username, Context.MODE_PRIVATE)
        if (sp.contains("username")) {
            sp.edit().clear().commit()
            Toast.makeText(context, "$username removed successfully", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "$username does not exist", Toast.LENGTH_LONG).show()
        }
    }

}