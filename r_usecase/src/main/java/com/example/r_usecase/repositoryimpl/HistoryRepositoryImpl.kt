package com.example.r_usecase.repositoryimpl

import com.example.r_usecase.common.HISTORY
import com.example.r_usecase.common.USERS
import com.example.z_entity.db.daos.HistoryDao
import com.example.z_entity.db.models.MyHistory
import com.example.z_entity.repository.AuthRepository
import com.example.z_entity.repository.HistoryRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 internal class HistoryRepositoryImpl @Inject constructor(
    private val local: HistoryDao,
    private val fireStore: FirebaseFirestore,
    private val authRepository: AuthRepository
) : HistoryRepository {

    private val fireStorePersonsPath =
        fireStore.collection(USERS).document(authRepository.currentUser?.email.toString())
            .collection(HISTORY)

    override fun getByOwnerId(ownerId: String): Flow<List<MyHistory>> {
        TODO("Not yet implemented")
    }

    override fun getForHome(count: Int): Flow<List<MyHistory>> {
        TODO("Not yet implemented")
    }

    override fun getHistory(): Flow<List<MyHistory>> {
        TODO("Not yet implemented")
    }

    override fun getHistory(limit: Int, page: Int): List<MyHistory> {
        return local.getHistory(limit, page)
    }

    override fun getHistoryCount(): Flow<Int> {
        TODO("Not yet implemented")
    }
}