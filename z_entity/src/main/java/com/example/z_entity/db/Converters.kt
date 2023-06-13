package com.example.z_entity.db

import androidx.room.TypeConverter
import com.example.z_entity.db.entity.MyWalletOwnerList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String): MyWalletOwnerList {
        val listType = object : TypeToken<MyWalletOwnerList>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: MyWalletOwnerList): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}