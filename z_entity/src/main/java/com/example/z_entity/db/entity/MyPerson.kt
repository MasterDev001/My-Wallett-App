package com.example.z_entity.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a_common.data.PersonData
import com.example.z_entity.db.remote_models.PersonRemote

@Entity(tableName = "persons")
data class MyPerson(
    @PrimaryKey
    val id: String,
    var name: String,
    var address: String,
    var phoneNumber: String,
    var date: Long,
    var uploaded: Boolean = false
) {
    fun toPersonRemote() = PersonRemote(id, name, address, phoneNumber, date)
}

fun MyPerson.toPersonData() = PersonData(id, name, address, phoneNumber, date, uploaded)
fun PersonData.toMyPerson() = MyPerson(id, name, address, phoneNumber, date, uploaded)

