package com.mikekorel.mobilebankingdemo.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TransactionHistoryBodyDto(
    @SerializedName("next_page")
    val nextPage: Int,
    @SerializedName("from_date")
    val fromDate: String? = null,
    @SerializedName("to_date")
    val toDate: String? = null
)