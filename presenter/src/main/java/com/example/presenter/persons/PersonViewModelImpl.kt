package com.example.presenter.persons

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonCurrencyData
import com.example.a_common.data.PersonData
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.personCurrencyUseCase.PersonCurrencyUseCase
import com.example.r_usecase.usecases.personUseCase.PersonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonViewModelImpl @Inject constructor(
    private val personsUseCase: PersonsUseCase,
    private val direction: PersonDirection,
    private val currencyUseCase: CurrencyUseCase,
    private val personCurrencyUseCase: PersonCurrencyUseCase
) : PersonViewModel {

    override val persons: Flow<List<PersonData>> = flow {
        emitAll(personsUseCase.getAllPersonsUseC.invoke())
    }

    override val uiState = MutableStateFlow(PersonContract.UiState(false))

    override fun getPerson(personId: String): PersonData {
        return personsUseCase.getPersonUseC.invoke(personId)
    }

    override fun getPersonCurriesByPersonId(personId: String): Flow<List<PersonCurrencyData>> =
        flow {
            emitAll(personCurrencyUseCase.getPersonCurriesByPersonIdUseC.invoke(personId))
        }

    override fun isPersonExist(name: String): Boolean {
        return personsUseCase.isPersonExistUseC.invoke(name)
    }

    override fun getCurrency(id: String): CurrencyData {
        return currencyUseCase.getCurrency.invoke(id)
    }

    override fun getAllDebtors(): Flow<List<PersonCurrencyData>> = flow {
        emitAll(personCurrencyUseCase.getAllDebtors.invoke())
    }

    override fun getAllLenders(): Flow<List<PersonCurrencyData>> = flow {
        emitAll(personCurrencyUseCase.getAllLenders.invoke())
    }

    override fun isPersonCurrencyExist(id: String): Boolean {
        return personCurrencyUseCase.isPersonCurrencyExistUseC.invoke(id)
    }

    private fun reduce(action: (PersonContract.UiState) -> PersonContract.UiState) {
        val oldState = uiState.value
        uiState.value = action(oldState)
    }

    override fun onEventDispatcher(intent: PersonContract.Intent) {
        when (intent) {
            is PersonContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }

            is PersonContract.Intent.OpenPersonHistory -> {
                coroutineScope.launch { direction.navigateToPersonHistory(intent.personData) }
            }

            is PersonContract.Intent.AddPerson -> {
                coroutineScope.launch(Dispatchers.IO) {
                    personsUseCase.addPersonUseC.invoke(intent.personData)
                }
            }

            is PersonContract.Intent.DeletePerson -> {
                coroutineScope.launch(Dispatchers.IO) {
                    personsUseCase.deletePersonUseC.invoke(intent.personId)
                }
            }

            is PersonContract.Intent.UpdatePerson -> {
                coroutineScope.launch(Dispatchers.IO) {
                    personsUseCase.updatePersonUseC.invoke(intent.personData)
                }
            }
        }
    }
}