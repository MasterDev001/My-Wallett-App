package com.example.r_usecase.usecases.historyUseCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.r_usecase.common.HistoryPagingSource
import com.example.z_entity.repository.HistoryRepository
import javax.inject.Inject

class GetHistoryPager @Inject constructor(private val historyRepository: HistoryRepository) {

    operator fun invoke() =
        Pager(config = PagingConfig(20), pagingSourceFactory = {
        HistoryPagingSource(historyRepository)
    }).flow
//        .cachedIn(GlobalScope)
}