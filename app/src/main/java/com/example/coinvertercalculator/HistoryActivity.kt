package com.example.coinvertercalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coinvertercalculator.app.User
import com.example.coinvertercalculator.data.ConversionResult
import com.example.coinvertercalculator.helper.ConversionResultAdapter

class HistoryActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ConversionResultAdapter
    lateinit var conversionHistory: MutableList<ConversionResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val button_back = findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener {
            finish()
        }

        listView = findViewById(R.id.conversionListView)

        conversionHistory = mutableListOf()

        conversionHistory.add(ConversionResult("USD", "EUR", 100.0, 90.0))
        conversionHistory.add(ConversionResult("GBP", "JPY", 50.0, 8000.0))
        conversionHistory.add(ConversionResult("CAD", "AUD", 20.0, 22.0))
        conversionHistory.add(ConversionResult("CAD", "AUD", 20.0, 22.0))
        conversionHistory.add(ConversionResult("USD", "EUR", 100.0, 90.0))
        conversionHistory.add(ConversionResult("GBP", "JPY", 50.0, 8000.0))
        conversionHistory.add(ConversionResult("USD", "EUR", 100.0, 90.0))
        conversionHistory.add(ConversionResult("CAD", "AUD", 20.0, 22.0))
        conversionHistory.add(ConversionResult("GBP", "JPY", 50.0, 8000.0))
        conversionHistory.add(ConversionResult("CAD", "AUD", 20.0, 22.0))
        conversionHistory.add(ConversionResult("USD", "EUR", 100.0, 90.0))
        conversionHistory.add(ConversionResult("GBP", "JPY", 50.0, 8000.0))

        adapter = ConversionResultAdapter(this, R.layout.list_item_conversion, conversionHistory)
        listView.adapter = adapter
    }
}