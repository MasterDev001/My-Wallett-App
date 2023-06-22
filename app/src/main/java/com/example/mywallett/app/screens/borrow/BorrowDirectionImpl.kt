package com.example.mywallett.app.screens.borrow

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.borrow.BorrowDirection
import javax.inject.Inject

class BorrowDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    BorrowDirection {

    override suspend fun back() {
        navigator.back()
    }
}