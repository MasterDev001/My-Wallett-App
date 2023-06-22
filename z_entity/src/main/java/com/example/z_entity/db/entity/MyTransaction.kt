package com.example.z_entity.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a_common.data.TransactionData
import com.example.z_entity.db.remote_models.TransactionRemote

@Entity(tableName = "transactions")
data class MyTransaction(
    @PrimaryKey
    var date: String,
    var type: Int = 0,
    var fromId: String,
    var toId: String,
    var currencyId: String,
    var amount: Double,
    var currencyFrom: String = "",
    var currencyTo: String = "",
    var comment: String = "",
    var uploaded: Boolean = false,

    var isFromPocket: Boolean = false,
    var isToPocket: Boolean = false,
    var rate: Double = 1.0,
    var rateFrom: Double = 1.0,
    var rateTo: Double = 1.0,
    var balance: Double = 0.0
) {
    fun toTransactionRemote() = TransactionRemote(
        date,
        type,
        fromId,
        toId,
        currencyId,
        amount,
        currencyFrom,
        currencyTo,
        comment,
        isFromPocket,
        isToPocket,
        rate,
        rateFrom,
        rateTo,
        balance
    )
}

fun MyTransaction.toTransactionData() = TransactionData(
    date,
    type,
    fromId,
    toId,
    currencyId,
    amount,
    currencyFrom,
    currencyTo,
    comment,
    isFromPocket,
    isToPocket,
    rate,
    rateFrom,
    rateTo,
    balance
)

fun TransactionData.toMyTransaction() = MyTransaction(
    date,
    type,
    fromId,
    toId,
    currencyId,
    amount,
    currencyFrom,
    currencyTo,
    comment,
    isFromPocket = isFromPocket,
    isToPocket = isToPocket,
    rate = rate,
    rateFrom = rateFrom,
    rateTo = rateTo,
    balance = balance
)
