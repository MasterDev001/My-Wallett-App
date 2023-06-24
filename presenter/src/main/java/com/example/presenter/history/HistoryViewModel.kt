package com.example.presenter.history

import androidx.paging.PagingData
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.a_common.data.HistoryData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(HistoryViewModelImpl::class)
interface HistoryViewModel : ScreenModel {

    val uiState: StateFlow<HistoryContract.UiState>
    val historyPager : Flow<PagingData<HistoryData>>

    fun onEventDispatcher(intent: HistoryContract.Intent)
}