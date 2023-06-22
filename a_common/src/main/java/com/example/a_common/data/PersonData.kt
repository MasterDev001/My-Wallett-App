package com.example.a_common.data

data class PersonData(
    val id: String,
    var name: String = "",
    var address: String = "",
    var phoneNumber: String = "",
    var date: Long = 0,
    var uploaded: Boolean = false
)
