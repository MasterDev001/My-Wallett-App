package com.example.presenter.borrow

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl

@ScreenModelImpl(BorrowViewModelImpl::class)
interface BorrowViewModel : ScreenModel {

    val uiState: StateFlow<BorrowContract.UiState>

    fun onEventDispatcher(intent: BorrowContract.Intent)

}