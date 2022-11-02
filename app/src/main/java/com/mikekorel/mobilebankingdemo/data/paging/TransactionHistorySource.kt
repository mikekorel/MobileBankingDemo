package com.mikekorel.mobilebankingdemo.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mikekorel.mobilebankingdemo.data.remote.Api
import com.mikekorel.mobilebankingdemo.data.remote.dto.TransactionHistoryBodyDto
import com.mikekorel.mobilebankingdemo.data.remote.dto.toDomainModel
import com.mikekorel.mobilebankingdemo.domain.model.Transaction
import timber.log.Timber
import javax.inject.Inject

class TransactionHistorySource(
    private val accountId: String,
    private val api: Api,
) : PagingSource<Int, Transaction>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Transaction> {
        try {
            val pageKey = params.key ?: 0     // key null for initial load
            params.loadSize
            val response = api.getTransactionHistory(
                accountId,
                TransactionHistoryBodyDto(
                    nextPage = pageKey
                )
            )
            return if (!response.isSuccessful) {
                Timber.tag("PAGING").e("Request failed: ${response.errorBody()}")
                LoadResult.Error(Exception("Request failed: ${response.errorBody()}"))
            } else {
                LoadResult.Page(
                    data = response.body()?.transactions?.map { it.toDomainModel() } ?: listOf(),
                    prevKey = null,     // only paging forward
                    nextKey = if (pageKey + 1 < (response.body()?.paging?.pagesCount ?: 0)
                    ) pageKey + 1 else null
                )
            }

        } catch (t: Throwable) {
            Timber.tag("PAGING").e(t)
            return LoadResult.Error(t)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Transaction>): Int? {
        return null
    }
}