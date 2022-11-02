package com.mikekorel.mobilebankingdemo.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PagingDto(
    @SerializedName("current_page")
    val currentPage: Int? = null,
    @SerializedName("pages_count")
    val pagesCount: Int? = null,
    @SerializedName("total_items")
    val totalItems: Int? = null
)