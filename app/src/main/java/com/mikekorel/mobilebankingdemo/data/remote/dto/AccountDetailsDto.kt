package com.mikekorel.mobilebankingdemo.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.mikekorel.mobilebankingdemo.domain.model.AccountDetails

data class AccountDetailsDto(
    @SerializedName("beneficiaries")
    val beneficiaries: List<String?>? = null,
    @SerializedName("branch")
    val branch: String? = null,
    @SerializedName("opened_date")
    val openedDate: String? = null,
    @SerializedName("product_name")
    val productName: String? = null,
)

fun AccountDetailsDto.toDomainModel() =
    AccountDetails(
        beneficiaries = beneficiaries,
        branch = branch,
        openedDate = openedDate,
        productName = productName
    )