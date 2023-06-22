package com.example.r_usecase.repositoryimpl

import com.example.r_usecase.common.PERSONS
import com.example.r_usecase.common.USERS
import com.example.z_entity.db.daos.PersonsDao
import com.example.z_entity.db.entity.MyPerson
import com.example.z_entity.repository.AuthRepository
import com.example.z_entity.repository.PersonsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class PersonsRepositoryImpl @Inject constructor(
    private val local: PersonsDao,
    fireStore: FirebaseFirestore,
    authRepository: AuthRepository
) : PersonsRepository {

    private val fireStorePersonsPath =
        fireStore.collection(USERS).document(authRepository.currentUser?.email.toString())
            .collection(PERSONS)

    override suspend fun addPerson(myPerson: MyPerson): Long {
        val result = local.addPerson(myPerson)

        fireStorePersonsPath.document(myPerson.id)
            .set(myPerson.toPersonRemote().copy(date = System.currentTimeMillis()))
            .addOnSuccessListener {
                runBlocking {
                    local.addPerson(
                        myPerson.copy(
                            date = System.currentTimeMillis(),
                            uploaded = true
                        )
                    )
                }
            }
        return result
    }

    override suspend fun updatePerson(myPerson: MyPerson): Int {
        val dataMap = hashMapOf<String, Any>()
        dataMap[myPerson.id] = myPerson.id
        dataMap[myPerson.name] = myPerson.name
        dataMap[myPerson.address] = myPerson.address
        dataMap[myPerson.phoneNumber] = myPerson.phoneNumber
        dataMap[myPerson.date.toString()] = System.currentTimeMillis()

        fireStorePersonsPath.document(myPerson.id)
            .update(dataMap).addOnSuccessListener {
                runBlocking {
                    local.updatePerson(
                        myPerson.copy(
                            date = System.currentTimeMillis(),
                            uploaded = true
                        )
                    )
                }
            }
        return local.updatePerson(myPerson)
    }

    override suspend fun deletePerson(personId: String): Int {
        fireStorePersonsPath.document(personId).delete().await()
        return local.deletePerson(personId)
    }

    override fun getPerson(personId: String): MyPerson {
        return local.getPerson(personId)
    }

    override fun isPersonExist(name: String): Boolean {
        return local.isPersonExists(name)
    }

    override suspend fun getAllPersons(): Flow<List<MyPerson>> {
        return local.getAllPersons()
    }

}