package com.example.z_entity.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.z_entity.db.entity.MyPerson
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPerson(person: MyPerson): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePerson(person: MyPerson): Int

    @Query("DELETE FROM persons WHERE id=:id ")
    suspend fun deletePerson(id: String): String

    @Query("SELECT * FROM persons WHERE id=:id limit 1")
    fun getPerson(id:String):MyPerson

    @Query("SELECT * FROM persons order by date")
    fun getAllPersons():Flow<List<MyPerson>>


}