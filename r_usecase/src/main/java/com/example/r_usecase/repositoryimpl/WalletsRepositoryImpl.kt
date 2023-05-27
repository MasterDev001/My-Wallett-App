package com.example.r_usecase.repositoryimpl

import com.example.r_usecase.common.CHILD_BALANCE
import com.example.r_usecase.common.CHILD_DATE
import com.example.r_usecase.common.CHILD_ID
import com.example.r_usecase.common.CHILD_NAME
import com.example.r_usecase.common.USERS
import com.example.r_usecase.common.WALLETS
import com.example.z_entity.db.daos.WalletsDao
import com.example.z_entity.db.entity.MyWallet
import com.example.z_entity.repository.AuthRepository
import com.example.z_entity.repository.WalletsRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WalletsRepositoryImpl @Inject constructor(
    private val local: WalletsDao,
    private val fireStore: FirebaseFirestore,
    private val authRepository: AuthRepository
) : WalletsRepository {

    override suspend fun addWallet(wallet: MyWallet): Long {
        val dataMap = hashMapOf<String, Any>()
        dataMap[CHILD_ID] = wallet.id
        dataMap[CHILD_NAME] = wallet.name
        dataMap[CHILD_BALANCE] = wallet.balance
        dataMap[CHILD_DATE] = wallet.date

        fireStore.collection(USERS)
            .document(authRepository.currentUser?.email.toString()).collection(WALLETS)
            .document(wallet.id).set(dataMap, SetOptions.merge()).addOnSuccessListener {

            }
        return local.addWallet(wallet)
    }

    override suspend fun updateWallet(wallet: MyWallet): Int {
        val dataMap = hashMapOf<String, Any>()
        dataMap[CHILD_ID] = wallet.id
        dataMap[CHILD_NAME] = wallet.name
        dataMap[CHILD_BALANCE] = wallet.balance
        dataMap[CHILD_DATE] = wallet.date

        fireStore.collection(USERS)
            .document(authRepository.currentUser?.email.toString()).collection(WALLETS)
            .document(wallet.id).update(dataMap)
            .addOnSuccessListener {}
        return local.updateWallet(wallet)
    }

    override suspend fun deleteWallet(wallet: MyWallet): Int {
        fireStore.collection(USERS)
            .document(authRepository.currentUser?.email.toString()).collection(WALLETS)
            .document(wallet.id).delete()
        return local.deleteWallet(wallet.id)
    }

    override suspend fun getWallet(name: String): Flow<MyWallet> {
        return local.getWallet(name)
    }

    override suspend fun getAllWallets(): Flow<List<MyWallet>> {
        return local.getAllWallets()
    }

}