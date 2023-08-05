package com.example.z_entity.db.remote_models

import com.example.z_entity.db.entity.MyTransaction

data class TransactionRemote (
    var date: String="",
    var type: Int = 0,
    var fromId: String = "",
    var toId: String = "",
    var currencyId: String = "",
    var amount: Double = 0.0,
    var currencyFrom: String = "",
    var currencyTo: String = "",
    var comment: String = "",

    var isFromPocket: Boolean = false,
    var isToPocket: Boolean = false,
    var rate: Double = 1.0,
    var rateFrom: Double = 1.0,
    var rateTo: Double = 1.0,
    var balance: Double = 0.0
) {

    fun toTransactionLocal() = MyTransaction(
        this.date,
        this.type,
        this.fromId,
        this.toId,
        this.currencyId,
        this.amount,
        this.currencyFrom,
        this.currencyTo,
        this.comment,
        uploaded = true,
        this.isFromPocket,
        this.isToPocket,
        this.rate,
        this.rateFrom,
        this.rateTo,
        this.balance
    )
}