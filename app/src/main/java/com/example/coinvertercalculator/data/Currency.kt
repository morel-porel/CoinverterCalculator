package com.example.coinvertercalculator.data

import com.example.coinvertercalculator.R

data class Currency (
    var currencyAcronym: String = "",
    var currencyName: String = "",
    var currencyRate: String = "",
    var currencyPhoto: Int = R.drawable.currency_exchange_24px
)