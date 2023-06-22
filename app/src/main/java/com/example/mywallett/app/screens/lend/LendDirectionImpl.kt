package com.example.mywallett.app.screens.lend

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.lend.LendDirection
import javax.inject.Inject

class LendDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    LendDirection {

    override suspend fun back() {
        navigator.back()
    }
}