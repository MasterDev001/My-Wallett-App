package com.example.z_entity.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a_common.data.PersonCurrencyData
import com.example.z_entity.db.remote_models.PersonCurrencyRemote

@Entity(tableName = "personCurrency")
data class MyPersonCurrency(
    @PrimaryKey
    var id: String,
    var personId: String,
    var currencyId: String,
    var currencyBalance: Double,
    var rate: Double,
    var uploaded: Boolean
) {
    fun toPersonCurrencyRemote() =
        PersonCurrencyRemote(id, personId, currencyId, currencyBalance, rate)
}

fun MyPersonCurrency.toPersonCurrencyData() =
    PersonCurrencyData(id, personId, currencyId, currencyBalance, rate, uploaded)

fun PersonCurrencyData.toMyPersonCurrency() =
    MyPersonCurrency(id, personId, currencyId, currencyBalance, rate, uploaded)
