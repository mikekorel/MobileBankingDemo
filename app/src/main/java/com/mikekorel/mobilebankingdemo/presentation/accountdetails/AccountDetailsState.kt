package com.mikekorel.mobilebankingdemo.presentation.accountdetails

import com.mikekorel.mobilebankingdemo.domain.model.AccountDetails

data class AccountDetailsState(
    val isLoading: Boolean = false,
    val accountDetails: AccountDetails = AccountDetails(),
    val error: String = ""
)
