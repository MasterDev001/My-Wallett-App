package com.example.z_entity.repository

interface DataRepository {

suspend fun loadNotLoadedData()
suspend fun isNeedUpdate():Boolean
suspend fun downloadAllData(isFinished:(Boolean)->Unit)
}