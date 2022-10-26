package com.mikekorel.mobilebankingdemo.domain.repository

import com.mikekorel.mobilebankingdemo.core.Resource
import com.mikekorel.mobilebankingdemo.domain.model.AccountDetails
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAccountsList() : Flow<Resource<List<AccountsListItem>>>

    fun getAccountDetails(accountId: String) : Flow<Resource<AccountDetails>>

}