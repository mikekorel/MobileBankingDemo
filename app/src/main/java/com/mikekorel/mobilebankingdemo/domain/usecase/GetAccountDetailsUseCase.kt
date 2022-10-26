package com.mikekorel.mobilebankingdemo.domain.usecase

import com.mikekorel.mobilebankingdemo.core.Resource
import com.mikekorel.mobilebankingdemo.domain.model.AccountDetails
import com.mikekorel.mobilebankingdemo.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountDetailsUseCase @Inject constructor(
    private val repository: Repository,
) {

    operator fun invoke(accountId: String): Flow<Resource<AccountDetails>> =
        repository.getAccountDetails(accountId)

}