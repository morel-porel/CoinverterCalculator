package com.example.coinvertercalculator.helper

import android.content.Context
import android.net.Uri
import android.widget.Toast

class UserPreferenceManager(private val context: Context) {

    fun userExists(username: String): Boolean {
        val sp = context.getSharedPreferences(username, Context.MODE_PRIVATE)
        return sp.contains("username")
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