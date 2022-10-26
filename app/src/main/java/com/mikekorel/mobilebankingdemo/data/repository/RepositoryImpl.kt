package com.mikekorel.mobilebankingdemo.data.repository

import com.mikekorel.mobilebankingdemo.core.Resource
import com.mikekorel.mobilebankingdemo.data.remote.Api
import com.mikekorel.mobilebankingdemo.data.remote.dto.toDomainModel
import com.mikekorel.mobilebankingdemo.domain.model.AccountDetails
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import com.mikekorel.mobilebankingdemo.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {

    override fun getAccountsList(): Flow<Resource<List<AccountsListItem>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val accounts = api.getAccountsList().map { it.toDomainModel() }
                emit(Resource.Success(accounts))
            } catch (t: Throwable) {
                emit(Resource.Error(t.localizedMessage ?: "An unexpected error occurred!"))
            }
        }
    }

    override fun getAccountDetails(accountId: String): Flow<Resource<AccountDetails>> {
        return flow {
            try {
                emit(Resource.Loading())
                val accountDetails = api.getAccountDetails(accountId).toDomainModel()
                emit(Resource.Success(accountDetails))
            } catch (t: Throwable) {
                emit(Resource.Error(t.localizedMessage ?: "An unexpected error occurred!"))
            }
        }
    }


}