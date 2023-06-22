package com.example.presenter.persons

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonCurrencyData
import com.example.a_common.data.PersonData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(PersonViewModelImpl::class)
interface PersonViewModel : ScreenModel {

    val uiState : StateFlow<PersonContract.UiState>
    val persons: Flow<List<PersonData>>

    fun getPersonCurriesByPersonId(personId: String): Flow<List<PersonCurrencyData>>
    fun getAllLenders(): Flow<List<PersonCurrencyData>>
    fun getAllDebtors(): Flow<List<PersonCurrencyData>>
    fun getPerson(personId: String): PersonData
    fun isPersonExist(name: String): Boolean
    fun isPersonCurrencyExist(id: String): Boolean
    fun getCurrency(id: String): CurrencyData
    fun onEventDispatcher(intent: PersonContract.Intent)
}