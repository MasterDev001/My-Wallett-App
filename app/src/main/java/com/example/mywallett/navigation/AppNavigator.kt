package com.example.mywallett.navigation

interface AppNavigator {
    suspend fun navigateTo(screen:AppScreen)
    suspend fun navigateTo(screens:List<AppScreen>)
    suspend fun replace(screen: AppScreen)
    suspend fun replaceAll(screen: AppScreen)
    suspend fun back()
    suspend fun <T:AppScreen> backUntil(clazz:Class<T>)
 }