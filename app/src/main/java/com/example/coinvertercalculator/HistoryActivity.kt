package com.example.coinvertercalculator

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.coinvertercalculator.app.User
import com.example.coinvertercalculator.data.ConversionResult
import com.example.coinvertercalculator.helper.ConversionResultAdapter
import com.example.coinvertercalculator.helper.UserPreferenceManager
import java.util.LinkedList
import java.util.Queue

class HistoryActivity : AppCompatActivity() {

    //static
    companion object{
        var conversionHistory: Queue<ConversionResult> = LinkedList()
    }

    private lateinit var listView: ListView
    private lateinit var adapter: ConversionResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val buttonBack = findViewById<Button>(R.id.button_back)
        buttonBack.setOnClickListener {
            finish()
        }

        listView = findViewById(R.id.conversionListView)
        adapter = ConversionResultAdapter(this, R.layout.list_item_conversion, mutableListOf())
        listView.adapter = adapter

        loadConversionHistory()
        updateListView()
    }

    override fun onResume() {
        super.onResume()
        updateListView()
    }

    private fun loadConversionHistory() {
        var userPrefsManager = UserPreferenceManager(this)
        var username = (application as User).username
        conversionHistory = userPrefsManager.getHistoryFromDevice(this, username)
    }

    private fun updateListView() {
        val reversedList = conversionHistory.reversed()
        adapter.clear()
        adapter.addAll(reversedList)
        adapter.notifyDataSetChanged()
    }
}
