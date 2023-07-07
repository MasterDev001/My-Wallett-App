package com.example.r_usecase.common

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.a_common.data.HistoryData
import com.example.z_entity.db.models.MyHistory
import com.example.z_entity.db.models.toHistoryData
import javax.inject.Inject

class HistoryPagingSource @Inject constructor(
    private val historyList:(Int,Int)->List<MyHistory>
) : PagingSource<Int, HistoryData>() {

    override fun getRefreshKey(state: PagingState<Int, HistoryData>): Int? {
//        return state.anchorPosition
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HistoryData> {
        return try {
            val page = params.key ?: 0
            val histories = historyList(20, page*20)
            LoadResult.Page(
                data = histories.map { it.toHistoryData() },
                prevKey = null,
//                prevKey = if (page == 0) null else page.minus(1),
//                nextKey = if (histories.size<params.loadSize) null else page+1
                nextKey = if (histories.isEmpty()) null else page+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}