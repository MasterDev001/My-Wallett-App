package com.example.r_usecase.repositoryimpl

import MyCurrency
import com.example.db.daos.CurrencyDao
import com.example.r_usecase.common.CHILD_DATE
import com.example.r_usecase.common.CHILD_ID
import com.example.r_usecase.common.CHILD_NAME
import com.example.r_usecase.common.CHILD_RATE
import com.example.r_usecase.common.CURRENCIES
import com.example.r_usecase.common.USERS
import com.example.repository.AuthRepository
import com.example.repository.CurrencyRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class CurrencyRepositoryImpl @Inject constructor(
    private val local: CurrencyDao,
    private val fireStore: FirebaseFirestore,
    private val authRepository: AuthRepository
) :
    CurrencyRepository {

    override suspend fun addCurrency(currency: MyCurrency): Long {
        val dataMap = hashMapOf<String, Any>()
        dataMap[CHILD_ID] = currency.id
        dataMap[CHILD_NAME] = currency.name
        dataMap[CHILD_DATE] = currency.date
        dataMap[CHILD_RATE] = currency.rate

        fireStore.collection(USERS)
            .document(authRepository.currentUser?.email.toString())
            .collection(CURRENCIES).document(currency.id).set(dataMap, SetOptions.merge())
            .addOnSuccessListener { }
        return local.addCurrency(currency)
    }

    override suspend fun updateCurrency(currency: MyCurrency): Int {
        val dataMap = hashMapOf<String, Any>()
        dataMap[CHILD_ID] = currency.id
        dataMap[CHILD_NAME] = currency.name
        dataMap[CHILD_DATE] = currency.date
        dataMap[CHILD_RATE] = currency.rate

        fireStore.collection(USERS)
            .document(authRepository.currentUser?.email.toString())
            .collection(CURRENCIES).document(currency.id).update(dataMap)
        return local.updateCurrency(currency)
    }

    override suspend fun deleteCurrency(currency: MyCurrency): Int {
        fireStore.collection(USERS)
            .document(authRepository.currentUser?.email.toString())
            .collection(CURRENCIES).document(currency.id).delete()
        return local.deleteCurrency(currency.id)
    }

    override suspend fun getCurrency(name: String): Flow<MyCurrency> {
        return local.getCurrency(name)
    }

    override suspend fun getAllCurrencies(): Flow<List<MyCurrency>> {
        return local.getAllCurrencies()
    }
}