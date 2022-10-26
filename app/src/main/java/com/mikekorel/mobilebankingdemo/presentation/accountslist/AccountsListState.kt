package com.mikekorel.mobilebankingdemo.presentation.accountslist

import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem

data class AccountsListState(
    val isLoading: Boolean = false,
    val accounts: List<AccountsListItem> = emptyList(),
    val error: String = ""
)
