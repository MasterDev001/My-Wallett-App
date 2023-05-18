package com.example.presenter.home

interface HomeDirection {

    suspend fun navigateToKirim()
    suspend fun navigateToChiqim()
    suspend fun navigateToQarzOlish()
    suspend fun navigateToQarzBerish()
    suspend fun navigateToHaqdorlar()
    suspend fun navigateToWallets()
    suspend fun navigateToHistory()
}