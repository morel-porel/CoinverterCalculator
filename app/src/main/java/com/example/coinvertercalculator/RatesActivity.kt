package com.example.coinvertercalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinvertercalculator.data.Currency
import com.example.coinvertercalculator.helper.CurrencyRecyclerViewAdapter

class RatesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rates)
        val currencyList = listOf(
            Currency("USD", "US dollar", "57.37", R.drawable.usd),
            Currency("EUR", "Euro", "62.09", R.drawable.eur),
            Currency("GBP", "Pound sterling", "74.25", R.drawable.gbp),
            Currency("JPY", "Japanese yen", "0.38", R.drawable.jpy),
            Currency("INR", "Indian rupee", "0.67", R.drawable.inr),
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CurrencyRecyclerViewAdapter(currencyList)

    }
}