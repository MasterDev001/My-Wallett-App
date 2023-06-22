package com.example.mywallett.app.screens.persons

import com.example.a_common.data.PersonData
import com.example.mywallett.app.screens.personHistory.PersonHistoryScreen
import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.persons.PersonDirection
import javax.inject.Inject

class PersonDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    PersonDirection {

    override suspend fun back() {
        navigator.back()
    }

    override suspend fun navigateToPersonHistory(personData: PersonData) {
        navigator.navigateTo(PersonHistoryScreen(personData))
    }
}