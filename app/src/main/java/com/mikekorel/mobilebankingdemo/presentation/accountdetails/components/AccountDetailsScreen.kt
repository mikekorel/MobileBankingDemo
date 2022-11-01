package com.mikekorel.mobilebankingdemo.presentation.accountdetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import com.mikekorel.mobilebankingdemo.presentation.accountslist.components.AccountsListItem

@Composable
fun AccountDetailsScreen(
    item: AccountsListItem? = null
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "AccountDetailsScreen !!")
         item?.let { account ->
             AccountsListItem(
                 account = account,
                 onItemClick = {}
             )
         }
    }
}