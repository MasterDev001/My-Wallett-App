package com.example.presenter.persons

import com.example.a_common.data.PersonData

class PersonContract {

    sealed interface Intent {
        object OpenHome : Intent
        class OpenPersonHistory(val personData: PersonData) : Intent
        class AddPerson(val personData: PersonData) : Intent
        class UpdatePerson(val personData: PersonData) : Intent
        class DeletePerson(val personId: String) : Intent
    }

    data class UiState(
        val isLoading: Boolean? = null,
        val message: String? = null,
        val error: String? = null,
    )
}