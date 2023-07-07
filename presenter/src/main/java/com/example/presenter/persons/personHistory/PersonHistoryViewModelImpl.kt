package com.example.presenter.persons.personHistory

import androidx.paging.PagingData
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.HistoryData
import com.example.a_common.data.PersonCurrencyData
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.historyUseCase.HistoryUseCase
import com.example.r_usecase.usecases.personCurrencyUseCase.PersonCurrencyUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PersonHistoryViewModelImpl @Inject constructor(
    private val historyUseCase: HistoryUseCase,
    private val direction: PersonHistoryDirection,
    private val personCurrencyUseCase: PersonCurrencyUseCase,
    private val currencyUseCase: CurrencyUseCase
) : PersonHistoryViewModel {

    override val uiState = MutableStateFlow(PersonHistoryContract.UiState(false))

    override fun personHistoryPager(ownerId: String): Flow<PagingData<HistoryData>> =
        flow {
            emitAll(historyUseCase.getHistoryByOwnerIdUseC.invoke(ownerId))
        }

    override fun getPersonCurriesByPersonId(personId: String): Flow<List<PersonCurrencyData>> =
        flow {
            emitAll(personCurrencyUseCase.getPersonCurriesByPersonIdUseC.invoke(personId))
        }

    override fun getCurrency(id: String): CurrencyData {
        return currencyUseCase.getCurrency.invoke(id)
    }

    override fun onEventDispatcher(intent: PersonHistoryContract.Intent) {
        when (intent) {
            is PersonHistoryContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }

        }
    }
}