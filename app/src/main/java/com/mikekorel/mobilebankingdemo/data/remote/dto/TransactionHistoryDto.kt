package com.mikekorel.mobilebankingdemo.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TransactionHistoryDto(
    @SerializedName("paging")
    val paging: PagingDto? = null,
    @SerializedName("transactions")
    val transactions: List<TransactionDto>? = null
)

