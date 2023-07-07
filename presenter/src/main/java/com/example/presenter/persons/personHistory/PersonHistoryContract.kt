package com.example.presenter.persons.personHistory

class PersonHistoryContract {

    sealed interface Intent {
        object OpenHome : Intent
    }

    data class UiState(
        val isLoading: Boolean? = null,
        val message: String? = null,
        val error: String? = null,
    )
}