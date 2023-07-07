package com.example.r_usecase.usecases.historyUseCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.r_usecase.common.HistoryPagingSource
import com.example.z_entity.repository.HistoryRepository
import javax.inject.Inject

class GetHistoryByOwnerIdUseC @Inject constructor(private val historyRepository: HistoryRepository) {

    operator fun invoke(ownerId:String) =
        Pager(config = PagingConfig(10), pagingSourceFactory = {
            HistoryPagingSource(historyList = { limit, page ->
                historyRepository.getByOwnerId(ownerId,limit, page)
            })
        }).flow
//        .cachedIn(GlobalScope)
}