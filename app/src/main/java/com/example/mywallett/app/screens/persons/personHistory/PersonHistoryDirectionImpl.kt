package com.example.mywallett.app.screens.persons.personHistory

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.persons.personHistory.PersonHistoryDirection
import javax.inject.Inject

class PersonHistoryDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    PersonHistoryDirection {

    override suspend fun back() {
        navigator.back()
    }

}