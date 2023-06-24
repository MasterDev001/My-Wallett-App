package com.example.mywallett.app.screens.history

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.history.HistoryDirection
import javax.inject.Inject

class HistoryDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    HistoryDirection {

    override suspend fun back() {
        navigator.back()
    }

}