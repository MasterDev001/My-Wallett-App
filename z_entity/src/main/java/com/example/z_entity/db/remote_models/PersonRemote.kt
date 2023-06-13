package com.example.z_entity.db.remote_models

data class PersonRemote(
    val id: String,
    var name: String,
    var address: String,
    var phoneNumber: String,
    var date: Long,
)