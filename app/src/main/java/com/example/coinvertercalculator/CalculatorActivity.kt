package com.example.coinvertercalculator

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val displayTop = findViewById<EditText>(R.id.display_top)
        val displayBottom = findViewById<EditText>(R.id.display_bottom)

        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val buttonDec = findViewById<Button>(R.id.button_dec)
        val buttonDel = findViewById<Button>(R.id.button_del)
        val buttonHistory = findViewById<Button>(R.id.button_history)
        val buttonCurChange = findViewById<Button>(R.id.button_curChange)
        val buttonClear = findViewById<Button>(R.id.button_clear)
        val buttonEqual = findViewById<Button>(R.id.button_equal)

        val currencySpinnerTop = findViewById<Spinner>(R.id.curChange_Top)
        val currencySpinnerBottom = findViewById<Spinner>(R.id.curChange_Bottom)

        val currencies = arrayOf("USD", "EUR", "GBP", "JPY", "INR")
        val adapterTop = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapterTop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySpinnerTop.adapter = adapterTop

        val adapterBottom = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapterBottom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currencySpinnerBottom.adapter = adapterBottom

        currencySpinnerTop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCurrencyTop = currencies[position]
                Toast.makeText(this@CalculatorActivity, "Top selected currency: $selectedCurrencyTop", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        currencySpinnerBottom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCurrencyBottom = currencies[position]
                Toast.makeText(this@CalculatorActivity, "Bottom selected currency: $selectedCurrencyBottom", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        button0.setOnClickListener { updateDisplay(displayTop, displayBottom, "0") }
        button1.setOnClickListener { updateDisplay(displayTop, displayBottom, "1") }
        button2.setOnClickListener { updateDisplay(displayTop, displayBottom, "2") }
        button3.setOnClickListener { updateDisplay(displayTop, displayBottom, "3") }
        button4.setOnClickListener { updateDisplay(displayTop, displayBottom, "4") }
        button5.setOnClickListener { updateDisplay(displayTop, displayBottom, "5") }
        button6.setOnClickListener { updateDisplay(displayTop, displayBottom, "6") }
        button7.setOnClickListener { updateDisplay(displayTop, displayBottom, "7") }
        button8.setOnClickListener { updateDisplay(displayTop, displayBottom, "8") }
        button9.setOnClickListener { updateDisplay(displayTop, displayBottom, "9") }

        buttonDec.setOnClickListener { updateDisplay(displayTop, displayBottom, ".") }
        buttonDel.setOnClickListener { deleteLastChar(displayTop, displayBottom) }
        buttonClear.setOnClickListener { clearDisplay(displayTop, displayBottom) }
        buttonEqual.setOnClickListener { calculateResult(displayTop, displayBottom) }
        buttonHistory.setOnClickListener { showHistory() }
        buttonCurChange.setOnClickListener { changeCurrency() }
    }

    fun updateDisplay(displayTop: EditText, displayBottom: EditText, value: String) {
        val currentTextTop = displayTop.text.toString()
        val currentTextBottom = displayBottom.text.toString()
        displayTop.setText(currentTextTop + value)
        displayBottom.setText(currentTextBottom + value)
    }

    fun calculateResult(displayTop: EditText, displayBottom: EditText) {
        val expression = displayTop.text.toString()
        try {
            val result = evaluateExpression(expression)
            displayBottom.setText(result.toString())
        } catch (e: Exception) {
            Toast.makeText(this, "Error in expression", Toast.LENGTH_SHORT).show()
        }
    }

    fun evaluateExpression(expression: String): Double {
        return try {
            val result = expression.toDouble()
            result
        } catch (e: Exception) {
            0.0
        }
    }

    fun clearDisplay(displayTop: EditText, displayBottom: EditText) {
        displayTop.setText("")
        displayBottom.setText("")
    }

    fun deleteLastChar(displayTop: EditText, displayBottom: EditText) {
        val currentTextTop = displayTop.text.toString()
        val currentTextBottom = displayBottom.text.toString()
        if (currentTextTop.isNotEmpty()) {
            displayTop.setText(currentTextTop.substring(0, currentTextTop.length - 1))
        }
        if (currentTextBottom.isNotEmpty()) {
            displayBottom.setText(currentTextBottom.substring(0, currentTextBottom.length - 1))
        }
    }

    fun showHistory() {
        Toast.makeText(this, "Showing history", Toast.LENGTH_SHORT).show()
    }

    fun changeCurrency() {
        Toast.makeText(this, "Change Currency", Toast.LENGTH_SHORT).show()
    }
}

