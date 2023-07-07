package com.example.r_usecase.usecases.historyUseCase

data class HistoryUseCase(
val getHistoryForPaging: GetHistoryPagerUseC,
val getHistoryByOwnerIdUseC: GetHistoryByOwnerIdUseC,
val getLimitedHistoryUseC: GetLimitedHistoryUseC
)
