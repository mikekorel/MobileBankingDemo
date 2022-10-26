package com.mikekorel.mobilebankingdemo.domain.model


data class AccountDetails(
    val beneficiaries: List<String?>? = null,
    val branch: String? = null,
    val openedDate: String? = null,
    val productName: String? = null
)
