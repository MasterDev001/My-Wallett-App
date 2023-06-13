package com.example.z_entity.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personWallet")
data class PersonWallet(
    @PrimaryKey
    val id: String,
    var ownerId: String,
    var currencyId: String,
    var balance: Double = 0.0,
    var date: Long = 0,
    var uploaded: Boolean = false
)
