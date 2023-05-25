package com.example.mywallett.app.screens.wallets

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.wallets.WalletsDirection
import javax.inject.Inject

class WalletsDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : WalletsDirection {

    override suspend fun back() {
        navigator.back()
    }
}