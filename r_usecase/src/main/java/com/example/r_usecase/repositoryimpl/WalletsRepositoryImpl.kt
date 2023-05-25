package com.example.r_usecase.repositoryimpl

import com.example.z_entity.db.daos.WalletsDao
import com.example.z_entity.db.entity.MyWallet
import com.example.z_entity.repository.AuthRepository
import com.example.z_entity.repository.WalletsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WalletsRepositoryImpl @Inject constructor(
    private val local: WalletsDao,
    private val fireStore: FirebaseFirestore,
    private val authRepository: AuthRepository
) : WalletsRepository {

    override suspend fun addWallet(wallet: MyWallet): Long {


        return local.addWallet(wallet)
    }

    override suspend fun updateWallet(wallet: MyWallet): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWallet(wallet: MyWallet): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getWallet(name: String): Flow<MyWallet> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWallets(): Flow<List<MyWallet>> {
        TODO("Not yet implemented")
    }

}