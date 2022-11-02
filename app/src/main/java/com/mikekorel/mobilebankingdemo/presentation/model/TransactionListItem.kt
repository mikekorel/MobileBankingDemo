package com.mikekorel.mobilebankingdemo.presentation.model

import com.mikekorel.mobilebankingdemo.domain.model.Transaction

sealed class TransactionListItem {

    data class TransactionUIModel(
        val id: String? = null,
        val date: String? = null,
        val description: String? = null,
        val transactionAmount: String? = null,
        val transactionType: String? = null,
        val isDebit: Boolean? = null
    ) : TransactionListItem()

    data class TransactionSeparator(
        val date: String
    ) : TransactionListItem()
}

fun Transaction.toUIModel() = TransactionListItem.TransactionUIModel(
    id = id,
    date = date,
    description = description,
    transactionAmount = transactionAmount,
    transactionType = transactionType,
    isDebit = isDebit
)
