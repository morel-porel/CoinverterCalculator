package com.example.coinvertercalculator

import android.os.Bundle
import android.widget.Button
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
            Currency("AED", "UAE Dirham", "15.63", R.drawable.aed),
            Currency("AUD", "Australian Dollar", "37.15", R.drawable.aud),
            Currency("BRL", "Brazilian Real", "11.33", R.drawable.brl),
            Currency("CAD", "Canadian Dollar", "42.71", R.drawable.cad),
            Currency("CHF", "Swiss Franc", "64.28", R.drawable.chf),
            Currency("CNY", "Chinese Yuan", "7.90", R.drawable.cny),
            Currency("CZK", "Czech Koruna", "2.52", R.drawable.czk),
            Currency("DKK", "Danish Krone", "8.32", R.drawable.dkk),
            Currency("EUR", "Euro", "62.09", R.drawable.eur),
            Currency("GBP", "Pound Sterling", "74.25", R.drawable.gbp),
            Currency("HKD", "Hong Kong Dollar", "7.33", R.drawable.hkd),
            Currency("HUF", "Hungarian Forint", "0.16", R.drawable.huf),
            Currency("IDR", "Indonesian Rupiah", "0.0036", R.drawable.idr),
            Currency("ILS", "Israeli Shekel", "15.65", R.drawable.ils),
            Currency("INR", "Indian Rupee", "0.67", R.drawable.inr),
            Currency("JPY", "Japanese Yen", "0.38", R.drawable.jpy),
            Currency("KRW", "South Korean Won", "0.044", R.drawable.krw),
            Currency("MXN", "Mexican Peso", "3.34", R.drawable.mxn),
            Currency("MYR", "Malaysian Ringgit", "12.08", R.drawable.myr),
            Currency("NOK", "Norwegian Krone", "5.27", R.drawable.nok),
            Currency("NZD", "New Zealand Dollar", "35.52", R.drawable.nzd),
            Currency("PHP", "Philippine Peso", "1.00", R.drawable.php),
            Currency("PLN", "Polish Zloty", "14.17", R.drawable.pln),
            Currency("RUB", "Russian Ruble", "0.63", R.drawable.rub),
            Currency("SAR", "Saudi Riyal", "15.31", R.drawable.sar),
            Currency("SEK", "Swedish Krona", "5.48", R.drawable.sek),
            Currency("SGD", "Singapore Dollar", "42.96", R.drawable.sgd),
            Currency("THB", "Thai Baht", "1.57", R.drawable.thb),
            Currency("TRY", "Turkish Lira", "1.80", R.drawable.try_flag),
            Currency("USD", "US Dollar", "57.37", R.drawable.usd),
            Currency("ZAR", "South African Rand", "3.08", R.drawable.zar)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CurrencyRecyclerViewAdapter(currencyList)

        val button_back = findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener {
            finish()
        }


    }
}