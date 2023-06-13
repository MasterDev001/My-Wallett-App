package com.example.mywallett.app.screens.income

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.income.InComeDirection
import javax.inject.Inject

class InComeDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    InComeDirection {

    override suspend fun back() {
        navigator.back()
    }
}