package com.mikekorel.mobilebankingdemo.domain.usecase

import com.mikekorel.mobilebankingdemo.core.Resource
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import com.mikekorel.mobilebankingdemo.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountsListUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<Resource<List<AccountsListItem>>> = repository.getAccountsList()

}