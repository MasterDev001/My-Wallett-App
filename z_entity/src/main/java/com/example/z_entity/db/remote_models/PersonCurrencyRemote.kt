package com.example.z_entity.db.remote_models

import com.example.z_entity.db.models.MyPersonCurrency

data class PersonCurrencyRemote(
    var id: String="",
    var personId: String="",
    var currencyId: String="",
    var currencyBalance: Double=0.0,
    var rate: Double=0.0
) {
    fun toPersonCurrencyLocal() =
        MyPersonCurrency(id, personId, currencyId, currencyBalance, rate, uploaded = true)
}

