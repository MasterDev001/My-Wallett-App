package com.example.z_entity.db.remote_models

data class TransactionRemote(
    var id: String = "",
    var type: Int = 0,
    var fromId: String = "",
    var toId: String = "",
    var currencyId: String = "",
    var amount: Double = 0.0,
    var currencyFrom: String = "",
    var currencyTo: String = "",
    var date: Long = 0,
    var comment: String = "",

    var isFromPocket: Boolean = false,
    var isToPocket: Boolean = false,
    var rate: Double = 1.0,
    var rateFrom: Double = 1.0,
    var rateTo: Double = 1.0,
    var balance: Double = 0.0
)