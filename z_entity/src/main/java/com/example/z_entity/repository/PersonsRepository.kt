package com.example.z_entity.repository

import com.example.z_entity.db.entity.MyPerson
import kotlinx.coroutines.flow.Flow

interface PersonsRepository {
    suspend fun addPerson(myPerson: MyPerson): Long
    suspend fun updatePerson(myPerson: MyPerson): Int
    suspend fun deletePerson(personId: String):Int
    fun getPerson(personId: String): MyPerson
    fun isPersonExist(name:String):Boolean
    suspend fun getAllPersons(): Flow<List<MyPerson>>

}