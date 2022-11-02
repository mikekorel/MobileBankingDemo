package com.mikekorel.mobilebankingdemo.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mikekorel.mobilebankingdemo.data.paging.TransactionHistorySource
import com.mikekorel.mobilebankingdemo.data.remote.Api
import com.mikekorel.mobilebankingdemo.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionHistoryUseCase @Inject constructor(
    private val api: Api
) {
    operator fun invoke(accountId: String): Flow<PagingData<Transaction>> {
        return Pager(
            config = PagingConfig(pageSize = TRANSACTIONS_PAGE_SIZE)
        ) {
            TransactionHistorySource(accountId, api)
        }.flow
    }
}

const val TRANSACTIONS_PAGE_SIZE = 10    // this value is ignored with our paging implementation