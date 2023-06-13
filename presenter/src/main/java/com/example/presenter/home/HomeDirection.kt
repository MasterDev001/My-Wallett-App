package com.example.presenter.home

interface HomeDirection {

    suspend fun navigateToInCome()
    suspend fun navigateToOutCome()
    suspend fun navigateToQarzOlish()
    suspend fun navigateToQarzBerish()
    suspend fun navigateToHaqdorlar()
    suspend fun navigateToWallets()
    suspend fun navigateToHistory()
    suspend fun navigateToCurrencies()
}