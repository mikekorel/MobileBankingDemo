package com.mikekorel.mobilebankingdemo.presentation.accountslist

import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem

data class AccountsListState(
    val accounts: List<AccountsListItem> = emptyList(),
    val favoriteAccount: AccountsListItem? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
