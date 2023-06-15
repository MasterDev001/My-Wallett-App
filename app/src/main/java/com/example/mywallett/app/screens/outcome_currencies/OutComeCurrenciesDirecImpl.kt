package com.example.mywallett.app.screens.outcome_currencies

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.outCome_currencies.OutComeCurrenciesDirection
import javax.inject.Inject

class OutComeCurrenciesDirecImpl @Inject constructor(private val navigator: AppNavigator) :
    OutComeCurrenciesDirection {

    override suspend fun back() {
        navigator.back()
    }
}