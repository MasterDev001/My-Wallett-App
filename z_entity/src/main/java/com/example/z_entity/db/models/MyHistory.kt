package com.example.z_entity.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a_common.data.HistoryData

@Entity
data class MyHistory(
    var title: Int = 0,
    var amount: Double = 0.0,
    var currency: String = "",
    var fromName: String? = null,
    var toName: String? = null,
    var moneyFrom: Double? = null,
    var moneyTo: Double? = null,
    var moneyNameFrom: String? = null,
    var moneyNameTo: String? = null,
    var comment: String? = null,
    @PrimaryKey
    var date: Long = 0,

    var isFromPocket: Boolean = false,
    var isToPocket: Boolean = false,
    var rate: Double = 1.0,
    var rateFrom: Double = 1.0,
    var rateTo: Double = 1.0,
    var balance: Double = 0.0,
    var transactionId: String? = null,
    var uploaded: Boolean = false
)

fun MyHistory.toHistoryData() = HistoryData(
    title,
    amount,
    currency,
    fromName,
    toName,
    moneyFrom,
    moneyTo,
    moneyNameFrom,
    moneyNameTo,
    comment,
    date,
    isFromPocket,
    isToPocket,
    rate,
    rateFrom,
    rateTo,
    balance,
    transactionId,
    uploaded
)
