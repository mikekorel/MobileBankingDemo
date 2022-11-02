package com.mikekorel.mobilebankingdemo.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.mikekorel.mobilebankingdemo.domain.model.Transaction

data class TransactionDto(
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("is_debit")
    val isDebit: Boolean? = null,
    @SerializedName("transaction_amount")
    val transactionAmount: String? = null,
    @SerializedName("transaction_type")
    val transactionType: String? = null
)

fun TransactionDto.toDomainModel() =
    Transaction(
        date = date,
        description = description,
        id = id,
        isDebit = isDebit,
        transactionAmount = transactionAmount,
        transactionType = transactionType
    )