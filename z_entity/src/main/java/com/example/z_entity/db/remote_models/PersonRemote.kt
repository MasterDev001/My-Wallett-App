package com.example.z_entity.db.remote_models

import com.example.z_entity.db.entity.MyPerson

data class PersonRemote(
    val id: String="",
    var name: String="",
    var address: String="",
    var phoneNumber: String="",
    var date: Long=0,
) {
    fun toPersonLocal() = MyPerson(id, name, address, phoneNumber, date, uploaded = true)
}