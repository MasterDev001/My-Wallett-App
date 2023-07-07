package com.example.r_usecase.usecases.historyUseCase

import com.example.z_entity.db.models.toHistoryData
import com.example.z_entity.repository.HistoryRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLimitedHistoryUseC @Inject constructor(private val historyRepository: HistoryRepository) {

    suspend operator fun invoke(count: Int) =
        historyRepository.getHistoryForHome(count).map { myHistoryList ->
            myHistoryList.map { it.toHistoryData() }
        }
}