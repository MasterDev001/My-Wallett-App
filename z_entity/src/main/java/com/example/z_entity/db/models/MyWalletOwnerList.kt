package com.example.z_entity.db.models

import com.example.a_common.data.WalletOwnerData
import com.example.a_common.data.WalletOwnerDataList

data class MyWalletOwnerList(
    val myWalletOwners: List<MyWalletOwner>
)

fun MyWalletOwnerList.toWalletOwnerDataList() =
    WalletOwnerDataList(myWalletOwners.map { myWalletOwner ->
        myWalletOwner.toWalletOwnerData()
    } as MutableList<WalletOwnerData>)

fun WalletOwnerDataList.toMyWalletOwnerList() =
    MyWalletOwnerList(walletOwnerData.map { walletOwner ->
        walletOwner.toMyWalletOwner()
    })
