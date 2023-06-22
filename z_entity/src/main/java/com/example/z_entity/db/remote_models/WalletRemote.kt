package com.example.z_entity.db.remote_models

import com.example.z_entity.db.models.MyWalletOwnerList

data class WalletRemote(
    val id: String,
    val name: String,
    val myWalletOwnerList: MyWalletOwnerList,
    val date: Long
)

