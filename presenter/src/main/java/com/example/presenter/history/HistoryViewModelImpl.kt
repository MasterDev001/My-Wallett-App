package com.example.presenter.history

import androidx.paging.PagingData
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.data.HistoryData
import com.example.r_usecase.usecases.historyUseCase.HistoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HistoryViewModelImpl @Inject constructor(
    private val direction: HistoryDirection,
    private val historyUseCase: HistoryUseCase,
) : HistoryViewModel {

    override val uiState = MutableStateFlow(HistoryContract.UiState.Default)

    override val historyPager :Flow<PagingData<HistoryData>> =
        historyUseCase.getHistoryForPaging.invoke()

    override fun onEventDispatcher(intent: HistoryContract.Intent) {
        when (intent) {
            is HistoryContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }

        }
    }
}