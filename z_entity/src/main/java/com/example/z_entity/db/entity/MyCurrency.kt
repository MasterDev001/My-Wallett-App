package com.example.z_entity.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a_common.data.CurrencyData
import com.example.z_entity.db.remote_models.CurrencyRemote

@Entity(tableName = "currencies")
data class MyCurrency(
    @PrimaryKey
    val id: String,
    var name: String,
    var rate: Double,
    var date: Long,
    var balance:Double=0.0,
    var uploaded: Boolean = false
) {
    fun toRemote() = CurrencyRemote(
        id = this.id,
        name = this.name,
        balance = this.balance,
        rate = this.rate,
        date = this.date
    )
}
    fun MyCurrency.toCurrencyData() = CurrencyData(id, name, rate,date, balance = balance)
    fun CurrencyData.toMyCurrency() = MyCurrency(id, name, rate, date, balance = balance)
