package com.example.z_entity.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class MyPerson(
    @PrimaryKey
    val id: String,
    var name: String,
    var address:String,
    val currencies:List<MyCurrency>,
    var phoneNumber: String,
    var date: Long,
    var uploaded: Boolean = false
)
