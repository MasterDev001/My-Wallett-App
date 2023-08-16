package com.example.mywallett.app.screens.share

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.share.ShareDirection
import javax.inject.Inject

class ShareDirectionImpl @Inject constructor(private val navigator: AppNavigator):ShareDirection {
    override suspend fun back() {
        navigator.back()
    }

}