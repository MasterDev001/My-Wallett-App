package com.example.r_usecase.repositoryimpl

import com.example.r_usecase.common.CHILD_DATE
import com.example.r_usecase.common.CHILD_ID
import com.example.r_usecase.common.CHILD_NAME
import com.example.r_usecase.common.CHILD_RATE
import com.example.r_usecase.common.CURRENCIES
import com.example.r_usecase.common.USERS
import com.example.z_entity.db.daos.CurrencyDao
import com.example.z_entity.db.entity.MyCurrency
import com.example.z_entity.repository.AuthRepository
import com.example.z_entity.repository.CurrencyRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class CurrencyRepositoryImpl @Inject constructor(
    private val local: CurrencyDao,
    private val fireStore: FirebaseFirestore,
    private val authRepository: AuthRepository
) : CurrencyRepository {

    override suspend fun addCurrency(currency: MyCurrency): Long {
        val dataMap = hashMapOf<String, Any>()
        dataMap[CHILD_ID] = currency.id
        dataMap[CHILD_NAME] = currency.name
        dataMap[CHILD_DATE] = currency.date
        dataMap[CHILD_RATE] = currency.rate

        fireStore.collection(USERS)
            .document(authRepository.currentUser?.email.toString())
            .collection(CURRENCIES).document(currency.id).set(dataMap, SetOptions.merge())
            .addOnSuccessListener {
                runBlocking {
                    local.addCurrency(currency.copy(uploaded = true))
                }
            }
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
            .collection(CURRENCIES).document(currency.id).update(dataMap).addOnSuccessListener {
                runBlocking {
                    local.updateCurrency(currency.copy(uploaded = true))
                }
            }
        return local.updateCurrency(currency)
    }

    override suspend fun deleteCurrency(currency: MyCurrency): Int {
        fireStore.collection(USERS)
            .document(authRepository.currentUser?.email.toString())
            .collection(CURRENCIES).document(currency.id).delete()
        return local.deleteCurrency(currency.id)
    }

    override fun getCurrency(id: String): MyCurrency {
        return local.getCurrency(id)
    }

    override fun isCurrencyExist(name: String): Boolean {
        return local.isCurrencyExists(name)
    }

    override suspend fun getAllCurrencies(): Flow<List<MyCurrency>> {
        return local.getAllCurrencies()
    }
}