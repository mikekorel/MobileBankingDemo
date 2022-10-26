package com.mikekorel.mobilebankingdemo.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem

data class AccountsListItemDto(
    @SerializedName("account_nickname")
    val accountNickname: String? = null,
    @SerializedName("account_number")
    val accountNumber: Int? = null,
    @SerializedName("account_type")
    val accountType: String? = null,
    @SerializedName("balance")
    val balance: String? = null,
    @SerializedName("currency_code")
    val currencyCode: String? = null,
    @SerializedName("id")
    val id: String? = null
)

fun AccountsListItemDto.toDomainModel() =
    AccountsListItem(
        accountNickname = accountNickname,
        accountNumber = accountNumber,
        accountType = accountType,
        balance = balance,
        currencyCode = currencyCode,
        id = id
    )