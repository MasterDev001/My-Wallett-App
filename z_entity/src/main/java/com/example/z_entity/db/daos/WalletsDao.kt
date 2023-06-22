package com.example.z_entity.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.z_entity.db.entity.MyWallet
import com.example.z_entity.db.models.MyWalletOwnerList
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWallet(wallet: MyWallet): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWallet(wallet: MyWallet): Int

    @Query("DELETE FROM wallets WHERE id=:id")
    suspend fun deleteWallet(id: String): Int

    @Query("SELECT EXISTS(SELECT 1 FROM wallets WHERE id = :walletId AND myWalletOwnerList LIKE '%' || :walletOwnerId || '%')")
    suspend fun isCurrencyIdExistsInWallet(walletId: String, walletOwnerId: String): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM wallets WHERE LOWER(name) = LOWER(:name))")
    fun isWalletNameExists(name: String): Boolean

    //    @Query("SELECT walletOwner.* FROM wallets JOIN w ON wallets.myWalletOwnerList LIKE '%' || walletOwner.id || '%' WHERE wallets.id = :walletId")
    @Query("SELECT myWalletOwnerList FROM wallets WHERE id = :walletId")
    fun getWalletOwnerList(walletId: String): Flow<MyWalletOwnerList>

//    @Query("SELECT currencies.* FROM wallets JOIN currencies ON wallets.id = currencies.id WHERE wallets.id =:id;")

    @Query("SELECT * FROM wallets ")
    fun getAllWallets(): Flow<List<MyWallet>>

//    @Query(
//        "SELECT currencies.name as name,\n" +
//                "SUM(currencyList) as amount,\n" +
//                "currencies.rate as rate \n" +
//                "FROM wallets,currencies \n" +
//                "WHERE wallets.currencyId=currencies.id \n" +
//                "AND wallets.ownerId NOT IN (SELECT id FROM persons) " +
//                "GROUP BY currencies.id \n" +
//                "ORDER BY currencies.name"
//    )
//    fun getBalances(): Flow<List<Balance>>

}