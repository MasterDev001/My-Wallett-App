package com.example.mywallett.app.screens.currencies

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.currency.CurrencyDirection
import javax.inject.Inject

class CurrencyDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    CurrencyDirection {

    override suspend fun back() {
        navigator.back()
    }
}