package com.example.r_usecase.repositoryimpl

import com.example.a_common.PERSON_CURRENCIES
import com.example.a_common.USERS
import com.example.z_entity.db.daos.PersonCurrencyDao
import com.example.z_entity.db.models.MyPersonCurrency
import com.example.z_entity.repository.AuthRepository
import com.example.z_entity.repository.PersonCurrencyRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class PersonCurrencyRepositoryImpl @Inject constructor(
    private val local: PersonCurrencyDao,
    fireStore: FirebaseFirestore,
    authRepository: AuthRepository
) : PersonCurrencyRepository {

    private val fireStorePersonsPath =
        fireStore.collection(USERS).document(authRepository.currentUser?.email.toString())
            .collection(PERSON_CURRENCIES)


    override suspend fun addPersonCurrency(personCurrency: MyPersonCurrency): Long {
        val result = local.addPersonCurrency(personCurrency)

        fireStorePersonsPath.document(personCurrency.id)
            .set(personCurrency.toPersonCurrencyRemote())
            .addOnSuccessListener {
                runBlocking {
                    local.addPersonCurrency(personCurrency.copy(uploaded = true))
                }
            }
        return result
    }

    override suspend fun updatePersonCurrency(personCurrency: MyPersonCurrency): Int {
        val dataMap = hashMapOf<String, Any>()
        dataMap[personCurrency.id] = personCurrency.id
        dataMap[personCurrency.personId] = personCurrency.personId
        dataMap[personCurrency.currencyId] = personCurrency.currencyId
        dataMap[personCurrency.currencyBalance.toString()] = personCurrency.currencyBalance
        dataMap[personCurrency.rate.toString()] = personCurrency.rate

        fireStorePersonsPath.document(personCurrency.id)
            .update(dataMap).addOnSuccessListener {
                runBlocking {
                    local.updatePersonCurrency(personCurrency.copy(uploaded = true))
                }
            }
        return local.updatePersonCurrency(personCurrency)
    }

    override suspend fun deletePersonCurrency(id: String): Int {
        fireStorePersonsPath.document(id).delete().await()
        return local.deletePersonCurrency(id)
    }

    override suspend fun getPersonCurriesByPersonId(personId: String): Flow<List<MyPersonCurrency>> {
        return local.getPersonCurriesByPersonId(personId)
    }

    override suspend fun getAllDebtors(): Flow<List<MyPersonCurrency>> {
        return local.getAllDebtors()
    }

    override suspend fun getAllLenders(): Flow<List<MyPersonCurrency>> {
        return local.getAllLenders()
    }

    override fun isPersonCurrencyExists(personId: String): Boolean {
        return local.isPersonCurrencyExists(personId)
    }

    override fun isPersonCurrenciesCurrencyExists(personId: String, currencyId: String): Boolean {
       return local.isPersonCurrenciesCurrencyExists(personId, currencyId)
    }

    override fun getPersonCurrency(personId: String, currencyId: String): MyPersonCurrency {
        return local.getPersonCurrency(personId, currencyId)
    }
}