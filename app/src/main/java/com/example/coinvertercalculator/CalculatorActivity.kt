package com.example.coinvertercalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {
    private var selectedCurrencyTop = "USD"
    private var selectedCurrencyBottom = "EUR"
    private var isUpdating = false
    private var lastEditedField: EditText? = null

    private lateinit var displayTop: EditText
    private lateinit var displayBottom: EditText

    private val exchangeRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.91,
        "GBP" to 0.78,
        "JPY" to 153.24,
        "INR" to 83.10,
        "AUD" to 1.51,
        "CAD" to 1.36,
        "CHF" to 0.91,
        "CNY" to 7.24,
        "HKD" to 7.83,
        "NZD" to 1.64,
        "SEK" to 10.73,
        "NOK" to 11.00,
        "SGD" to 1.35,
        "KRW" to 1370.50,
        "MXN" to 16.68,
        "BRL" to 5.10,
        "ZAR" to 18.70,
        "RUB" to 93.00,
        "TRY" to 32.20,
        "AED" to 3.67,
        "SAR" to 3.75,
        "ILS" to 3.70,
        "THB" to 36.60,
        "MYR" to 4.76,
        "IDR" to 15900.0,
        "PHP" to 56.00,
        "PLN" to 3.95,
        "CZK" to 23.10,
        "HUF" to 351.50,
        "DKK" to 6.80
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        displayTop = findViewById(R.id.display_top)
        displayBottom = findViewById(R.id.display_bottom)
        val currencySpinnerTop = findViewById<Spinner>(R.id.curChange_Top)
        val currencySpinnerBottom = findViewById<Spinner>(R.id.curChange_Bottom)

        // Disable soft keyboard for both fields
        disableKeyboard(displayTop)
        disableKeyboard(displayBottom)

        setupCurrencySpinners(currencySpinnerTop, currencySpinnerBottom)
        setupNumberButtons()
        setupFunctionButtons()

        displayTop.addTextChangedListener(createTextWatcher(displayTop, displayBottom))
        displayBottom.addTextChangedListener(createTextWatcher(displayBottom, displayTop))

        val button_back = findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener {
            finish()
        }
    }

    private fun disableKeyboard(editText: EditText) {
        editText.showSoftInputOnFocus = false
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
    }

    private fun setupCurrencySpinners(topSpinner: Spinner, bottomSpinner: Spinner) {
        val currencies = exchangeRates.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        topSpinner.adapter = adapter
        bottomSpinner.adapter = adapter

        topSpinner.onItemSelectedListener = createSpinnerListener {
            selectedCurrencyTop = it
            updateBasedOnLastEdited()
        }

        bottomSpinner.onItemSelectedListener = createSpinnerListener {
            selectedCurrencyBottom = it
            updateBasedOnLastEdited()
        }
    }

    private fun createSpinnerListener(action: (String) -> Unit) = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            action(parent.getItemAtPosition(position) as String)
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }

    private fun setupNumberButtons() {
        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button_dec
        )
        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { button ->
                val inputField = if (displayTop.hasFocus()) displayTop else displayBottom
                inputField.append((button as Button).text.toString())
            }
        }
    }

    private fun setupFunctionButtons() {
        findViewById<Button>(R.id.button_del).setOnClickListener {
            val inputField = if (displayTop.hasFocus()) displayTop else displayBottom
            deleteLastChar(inputField)
        }
        findViewById<Button>(R.id.button_clear).setOnClickListener {
            displayTop.text.clear()
            displayBottom.text.clear()
        }
    }

    private fun createTextWatcher(inputField: EditText, outputField: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return
                lastEditedField = inputField
                changeCurrency(inputField, outputField)
            }
        }
    }

    private fun changeCurrency(inputField: EditText, outputField: EditText) {
        if (isUpdating) return
        isUpdating = true

        val inputAmount = inputField.text.toString().toDoubleOrNull()
        val rateFrom = if (inputField == displayTop) {
            exchangeRates[selectedCurrencyTop]
        } else {
            exchangeRates[selectedCurrencyBottom]
        } ?: return

        val rateTo = if (inputField == displayTop) {
            exchangeRates[selectedCurrencyBottom]
        } else {
            exchangeRates[selectedCurrencyTop]
        } ?: return

        outputField.setText(
            if (inputAmount == null) "" else "%.2f".format(inputAmount * (rateTo / rateFrom))
        )

        isUpdating = false
    }

    private fun updateBasedOnLastEdited() {
        when (lastEditedField) {
            displayTop -> changeCurrency(displayTop, displayBottom)
            displayBottom -> changeCurrency(displayBottom, displayTop)
        }
    }

    private fun deleteLastChar(inputField: EditText) {
        inputField.text.let {
            if (it.isNotEmpty()) it.delete(it.length - 1, it.length)
        }
    }
}
