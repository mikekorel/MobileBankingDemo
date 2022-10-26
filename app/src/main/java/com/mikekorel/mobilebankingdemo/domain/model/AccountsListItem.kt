package com.mikekorel.mobilebankingdemo.domain.model

data class AccountsListItem(
    val accountNickname: String? = null,
    val accountNumber: Int? = null,
    val accountType: String? = null,
    val balance: String? = null,
    val currencyCode: String? = null,
    val id: String? = null
)
