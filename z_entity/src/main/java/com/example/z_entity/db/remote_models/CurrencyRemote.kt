package com.example.z_entity.db.remote_models

import com.example.z_entity.db.entity.MyCurrency


data class CurrencyRemote(
    val id: String = "",
    var name: String = "",
    var rate: Double = 0.0,
    var balance: Double = 0.0,
    var date: Long = 0
) {
    fun toLocal() = MyCurrency(
        id = this.id,
        name = this.name,
        rate = this.rate,
        date = this.date,
        balance = balance,
        uploaded = true
    )
}

