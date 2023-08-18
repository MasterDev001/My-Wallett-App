package com.example.z_entity.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.z_entity.db.models.MyHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query(
        "SELECT transactions.type AS title, " +
                "transactions.amount AS amount,\n" +
                "currencies.name AS currency, \n" +
                "(SELECT name FROM persons WHERE id=transactions.fromId " +
                "UNION " +
                "SELECT name FROM wallets WHERE id=transactions.fromId) AS fromName,\n" +
                "(SELECT name FROM persons WHERE id=transactions.toId " +
                "UNION " +
                "SELECT name FROM wallets WHERE id=transactions.toId) as toName,\n" +
                "(SELECT (1/transactions.rate)*transactions.amount*transactions.rateFrom) AS moneyFrom, \n" +
                "(SELECT (1/transactions.rate)*transactions.amount*transactions.rateTo) AS moneyTo, \n" +
                "(SELECT name FROM currencies WHERE currencies.id=transactions.currencyFrom limit 1) AS moneyNameFrom, \n" +
                "(SELECT name FROM currencies WHERE currencies.id=transactions.currencyTo limit 1) AS moneyNameTo, \n" +
                "transactions.comment AS comment, transactions.date AS date, \n " +
                "transactions.isFromPocket AS isFromPocket, transactions.isToPocket AS isToPocket, \n" +
                "transactions.rate AS rate, transactions.rateFrom AS rateFrom, \n" +
                "transactions.rateTo AS rateTo, transactions.balance AS balance, \n" +
                "transactions.date AS transactionId, transactions.uploaded AS uploaded \n" +
                "FROM currencies, transactions where transactions.currencyId=currencies.id AND (transactions.fromId=:ownerId " +
                "OR transactions.toId=:ownerId) ORDER BY date DESC " +
                "LIMIT :limit OFFSET :page"
    )
    fun getByOwnerId(ownerId: String,limit: Int,page: Int): List<MyHistory>

    @Query(
        "SELECT transactions.type AS title, " +
                "transactions.amount AS amount,\n" +
                "currencies.name AS currency, \n" +
                "(SELECT name FROM persons WHERE id=transactions.fromId " +
                "UNION " +
                "SELECT name FROM wallets WHERE id=transactions.fromId) AS fromName,\n" +
                "(SELECT name FROM persons WHERE id=transactions.toId " +
                "UNION " +
                "SELECT name FROM wallets WHERE id=transactions.toId) as toName,\n" +
                "(SELECT transactions.amount) AS moneyFrom, \n" +
//                "(SELECT (1/transactions.rate)*transactions.amount*transactions.rateFrom) AS moneyFrom, \n" +
                "(SELECT transactions.amount*transactions.rateTo) AS moneyTo, \n" +
//                "(SELECT (1/transactions.rate)*transactions.amount*transactions.rateTo) AS moneyTo, \n" +
                "(SELECT name FROM currencies WHERE currencies.id=transactions.currencyFrom limit 1) AS moneyNameFrom, \n" +
                "(SELECT name FROM currencies WHERE currencies.id=transactions.currencyTo limit 1) AS moneyNameTo, \n" +
                "transactions.comment AS comment, transactions.date AS date, \n " +
                "transactions.isFromPocket AS isFromPocket, transactions.isToPocket AS isToPocket, \n" +
                "transactions.rate AS rate, transactions.rateFrom AS rateFrom, \n" +
                "transactions.rateTo AS rateTo, transactions.balance AS balance, \n" +
                "transactions.date AS transactionId, transactions.uploaded AS uploaded \n" +
                "FROM currencies, transactions where transactions.currencyId=currencies.id ORDER BY date DESC limit :count"
    )
    fun getForHome(count: Int): Flow<List<MyHistory>>


//    @Query(
//        "SELECT transactions.type AS title, " +
//                "transactions.amount AS amount,\n" +
//                "currencies.name AS currency, \n" +
//                "(SELECT name FROM persons WHERE id=transactions.fromId " +
//                "UNION " +
//                "SELECT name FROM wallets WHERE id=transactions.fromId) AS fromName,\n" +
//                "(SELECT name FROM persons WHERE id=transactions.toId " +
//                "UNION " +
//                "SELECT name FROM wallets WHERE id=transactions.toId) as toName,\n" +
//                "(SELECT (1/transactions.rate)*transactions.amount*transactions.rateFrom) AS moneyFrom, \n" +
//                "(SELECT (1/transactions.rate)*transactions.amount*transactions.rateTo) AS moneyTo, \n" +
//                "(SELECT name FROM currencies WHERE currencies.id=transactions.currencyFrom limit 1) AS moneyNameFrom, \n" +
//                "(SELECT name FROM currencies WHERE currencies.id=transactions.currencyTo limit 1) AS moneyNameTo, \n" +
//                "transactions.comment AS comment, transactions.date AS date, \n " +
//                "transactions.isFromPocket AS isFromPocket, transactions.isToPocket AS isToPocket, \n" +
//                "transactions.rate AS rate, transactions.rateFrom AS rateFrom, \n" +
//                "transactions.rateTo AS rateTo, transactions.balance AS balance, \n" +
//                "transactions.date AS transactionId, transactions.uploaded AS uploaded \n" +
//                "FROM currencies, transactions where transactions.currencyId=currencies.id ORDER BY date DESC"
//    )
//    fun getHistory(): Flow<List<MyHistory>>

    @Query(
        "SELECT transactions.type AS title, " +
                "transactions.amount AS amount,\n" +
                "currencies.name AS currency, \n" +
                "(SELECT name FROM persons WHERE id=transactions.fromId " +
                "UNION " +
                "SELECT name FROM wallets WHERE id=transactions.fromId) AS fromName,\n" +
                "(SELECT name FROM persons WHERE id=transactions.toId " +
                "UNION " +
                "SELECT name FROM wallets WHERE id=transactions.toId) as toName,\n" +
                "(SELECT (1/transactions.rate)*transactions.amount*transactions.rateFrom) AS moneyFrom, \n" +
                "(SELECT (1/transactions.rate)*transactions.amount*transactions.rateTo) AS moneyTo, \n" +
                "(SELECT name FROM currencies WHERE currencies.id=transactions.currencyFrom limit 1) AS moneyNameFrom, \n" +
                "(SELECT name FROM currencies WHERE currencies.id=transactions.currencyTo limit 1) AS moneyNameTo, \n" +
                "transactions.comment AS comment, transactions.date AS date, \n " +
                "transactions.isFromPocket AS isFromPocket, transactions.isToPocket AS isToPocket, \n" +
                "transactions.rate AS rate, transactions.rateFrom AS rateFrom, \n" +
                "transactions.rateTo AS rateTo, transactions.balance AS balance, \n" +
                "transactions.date AS transactionId, transactions.uploaded AS uploaded \n" +
                "FROM currencies, transactions where transactions.currencyId=currencies.id ORDER BY date DESC " +
                "LIMIT :limit OFFSET :page"
    )

    fun getHistory(limit: Int, page: Int): List<MyHistory>

    @Query("SELECT COUNT(*) FROM transactions")
    fun getHistoryCount(): Flow<Int>

}