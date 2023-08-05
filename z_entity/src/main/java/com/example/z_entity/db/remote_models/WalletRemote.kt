package com.example.z_entity.db.remote_models

import com.example.z_entity.db.entity.MyWallet
import com.example.z_entity.db.models.MyWalletOwner
import com.example.z_entity.db.models.MyWalletOwnerList

data class WalletRemote (
    val id: String = "",
    val name: String = "",
    val myWalletOwnerList: MyWalletOwnerList = MyWalletOwnerList(
        listOf(MyWalletOwner("", "", "", 0.0, 0.0))
    ),
    val date: Long = 0
) {
    constructor():this("")
    fun  toWalletLocal() = MyWallet(id, name, myWalletOwnerList, date, uploaded = true)
}

