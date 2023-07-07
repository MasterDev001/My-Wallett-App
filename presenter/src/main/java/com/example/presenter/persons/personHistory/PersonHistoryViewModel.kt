package com.example.presenter.persons.personHistory

import androidx.paging.PagingData
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.HistoryData
import com.example.a_common.data.PersonCurrencyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(PersonHistoryViewModelImpl::class)
interface PersonHistoryViewModel : ScreenModel {

    val uiState : StateFlow<PersonHistoryContract.UiState>

    fun personHistoryPager(ownerId:String) : Flow<PagingData<HistoryData>>
    fun getPersonCurriesByPersonId(personId: String): Flow<List<PersonCurrencyData>>
    fun getCurrency(id: String): CurrencyData
    fun onEventDispatcher(intent: PersonHistoryContract.Intent)
}