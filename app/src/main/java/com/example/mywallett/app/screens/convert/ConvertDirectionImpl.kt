package com.example.mywallett.app.screens.convert

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.convert.ConvertDirection
import javax.inject.Inject

class ConvertDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    ConvertDirection {

    override suspend fun back() {
        navigator.back()
    }

}