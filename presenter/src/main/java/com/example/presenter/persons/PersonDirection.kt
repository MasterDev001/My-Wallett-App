package com.example.presenter.persons

import com.example.a_common.data.PersonData

interface PersonDirection {

    suspend fun back()
    suspend fun navigateToPersonHistory(personData: PersonData)
}