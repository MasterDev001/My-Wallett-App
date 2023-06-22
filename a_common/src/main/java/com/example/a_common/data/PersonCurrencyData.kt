package com.example.a_common.data

data class PersonCurrencyData(
    var id: String,
    var personId: String,
    var currencyId: String,
    var currencyBalance: Double,
    var rate: Double,
    var uploaded:Boolean=false
)