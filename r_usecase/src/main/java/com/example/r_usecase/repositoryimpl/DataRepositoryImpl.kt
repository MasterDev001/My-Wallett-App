package com.example.r_usecase.repositoryimpl

import com.example.r_usecase.common.CHILD_DATE
import com.example.r_usecase.common.CURRENCIES
import com.example.r_usecase.common.PERSONS
import com.example.r_usecase.common.PERSON_CURRENCIES
import com.example.r_usecase.common.TRANSACTIONS
import com.example.r_usecase.common.USERS
import com.example.r_usecase.common.WALLETS
import com.example.z_entity.db.daos.CurrencyDao
import com.example.z_entity.db.daos.PersonCurrencyDao
import com.example.z_entity.db.daos.PersonsDao
import com.example.z_entity.db.daos.TransactionDao
import com.example.z_entity.db.daos.WalletsDao
import com.example.z_entity.db.remote_models.CurrencyRemote
import com.example.z_entity.db.remote_models.PersonCurrencyRemote
import com.example.z_entity.db.remote_models.PersonRemote
import com.example.z_entity.db.remote_models.TransactionRemote
import com.example.z_entity.db.remote_models.WalletRemote
import com.example.z_entity.repository.AuthRepository
import com.example.z_entity.repository.DataRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class DataRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val currencyDao: CurrencyDao,
    private val personsDao: PersonsDao,
    private val walletsDao: WalletsDao,
    private val personCurrencyDao: PersonCurrencyDao,
    private val fireStore: FirebaseFirestore,
    private val authRepository: AuthRepository
) : DataRepository {

    override suspend fun isNeedUpdate(): Boolean {
        return transactionDao.isNeedUpdate()
                || currencyDao.isNeedUpdate()
                || personCurrencyDao.isNeedUpdate()
                || personsDao.isNeedUpdate()
                || walletsDao.isNeedUpdate()
    }

    override suspend fun downloadAllData(isFinished: (Boolean) -> Unit) {
        if (authRepository.currentUser != null) {
            val fireStorePath =
                fireStore.collection(USERS).document(authRepository.currentUser?.email.toString())

            coroutineScope {
                launch(Dispatchers.IO) {
                    fireStorePath.collection(TRANSACTIONS).orderBy(CHILD_DATE).limitToLast(100)
                        .get().addOnSuccessListener { snapshot ->
                            if (!snapshot.isEmpty) {
                                val list =
                                    snapshot.map {
                                        it.toObject(TransactionRemote::class.java)
                                    }
                                runBlocking {
                                    transactionDao.addTransactions(list.map { it.toTransactionLocal() })
                                }
                            }
                        }.await()

                    fireStorePath.collection(CURRENCIES).get().addOnSuccessListener { snapshot ->
                        if (!snapshot.isEmpty) {
                            val list = snapshot.map { it.toObject(CurrencyRemote::class.java) }
                            runBlocking { currencyDao.addCurrencyList(list.map { it.toLocal() }) }
                        }
                    }.await()

                    fireStorePath.collection(PERSON_CURRENCIES).get()
                        .addOnSuccessListener { snapshot ->
                            if (!snapshot.isEmpty) {
                                val list =
                                    snapshot.map { it.toObject(PersonCurrencyRemote::class.java) }
                                runBlocking { personCurrencyDao.addPersonCurrencyList(list.map { it.toPersonCurrencyLocal() }) }
                            }
                        }.await()

                    fireStorePath.collection(PERSONS).get().addOnSuccessListener { snapshot ->
                        if (!snapshot.isEmpty) {
                            val list = snapshot.map { it.toObject(PersonRemote::class.java) }
                            runBlocking { personsDao.addPersonList(list.map { it.toPersonLocal() }) }
                        }
                    }.await()

                    fireStorePath.collection(WALLETS).get().addOnSuccessListener { snapshot ->
                        if (!snapshot.isEmpty) {
                            val list = snapshot.map { it.toObject(WalletRemote::class.java) }
                            runBlocking {
                                walletsDao.addWalletList(list.map { it.toWalletLocal() })
                            }
                        }
                    }.await()
                }
                isFinished(true)
            }
        }
    }

    override suspend fun loadNotLoadedData() {
        if (authRepository.currentUser != null) {
            val fireStorePath =
                fireStore.collection(USERS).document(authRepository.currentUser?.email.toString())
            coroutineScope {
                launch(Dispatchers.IO) {
                    val t = transactionDao.getNotUploaded()
                    val c = currencyDao.getNotUploaded()
                    val p = personsDao.getNotUploaded()
                    val pC = personCurrencyDao.getNotUploaded()
                    val w = walletsDao.getNotUploaded()
                    combine(
                        t, c, p, pC, w
                    ) { transactions, currencies, persons, personCurrencies, wallets ->
                        val task1 = async {
                            transactions.forEach { tr ->
                                fireStorePath.collection(TRANSACTIONS).document(tr.date)
                                    .set(tr.toTransactionRemote()).addOnSuccessListener {
                                        runBlocking {
                                            transactionDao.update(tr.copy(uploaded = true))
                                        }
                                    }
                            }
                        }
                        val task2 = async {
                            currencies.forEach { cr ->
                                fireStorePath.collection(CURRENCIES).document(cr.id)
                                    .set(cr.toRemote())
                                    .addOnSuccessListener {
                                        runBlocking {
                                            currencyDao.updateCurrency(cr.copy(uploaded = true))
                                        }
                                    }
                            }
                        }
                        val task3 = async {
                            personCurrencies.forEach { pcr ->
                                fireStorePath.collection(PERSON_CURRENCIES).document(pcr.id)
                                    .set(pcr.toPersonCurrencyRemote()).addOnSuccessListener {
                                        runBlocking {
                                            personCurrencyDao.updatePersonCurrency(pcr.copy(uploaded = true))
                                        }
                                    }
                            }
                        }
                        val task4 = async {
                            persons.forEach { pr ->
                                fireStorePath.collection(PERSONS).document(pr.id)
                                    .set(pr.toPersonRemote()).addOnSuccessListener {
                                        runBlocking {
                                            personsDao.updatePerson(pr.copy(uploaded = true))
                                        }
                                    }
                            }
                        }
                        val task5 = async {
                            wallets.forEach { w ->
                                fireStorePath.collection(WALLETS).document(w.id)
                                    .set(w.toWalletRemote())
                                    .addOnSuccessListener {
                                        runBlocking {
                                            walletsDao.updateWallet(w.copy(uploaded = true))
                                        }
                                    }
                            }
                        }
                        task1.await()
                        task2.await()
                        task3.await()
                        task4.await()
                        task5.await()
                    }.collect()
                }
            }
        }
    }
}
