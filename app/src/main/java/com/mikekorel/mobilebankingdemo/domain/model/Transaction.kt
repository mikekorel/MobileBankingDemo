package com.mikekorel.mobilebankingdemo.domain.model

import com.google.gson.annotations.SerializedName

data class Transaction(
    val id: String? = null,
    val date: String? = null,
    val description: String? = null,
    val transactionAmount: String? = null,
    val transactionType: String? = null,
    val isDebit: Boolean? = null
)
