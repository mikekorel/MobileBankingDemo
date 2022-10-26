package com.mikekorel.mobilebankingdemo.data.remote

import com.mikekorel.mobilebankingdemo.data.remote.dto.AccountDetailsDto
import com.mikekorel.mobilebankingdemo.data.remote.dto.AccountsListItemDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("accounts")
    suspend fun getAccountsList() : List<AccountsListItemDto>

    @GET("account/details/{account_id}")
    suspend fun getAccountDetails(
        @Path("account_id") accountId: String
    ) : AccountDetailsDto

}