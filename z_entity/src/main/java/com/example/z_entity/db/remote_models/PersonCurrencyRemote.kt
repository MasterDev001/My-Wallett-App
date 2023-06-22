package com.example.z_entity.db.remote_models

data class PersonCurrencyRemote(
    var id: String,
    var personId: String,
    var currencyId: String,
    var currencyBalance: Double,
    var rate: Double
)
