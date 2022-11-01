package com.mikekorel.mobilebankingdemo.presentation.accountdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import com.mikekorel.mobilebankingdemo.presentation.accountdetails.AccountDetailsViewModel
import com.mikekorel.mobilebankingdemo.presentation.accountslist.components.AccountsListItem

@Composable
fun AccountDetailsScreen(
    item: AccountsListItem? = null,
    viewModel: AccountDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Column(modifier = Modifier.fillMaxSize()) {
         item?.let { account ->
             AccountsListItem(
                 account = account,
                 onItemClick = {}
             )
             Spacer(modifier = Modifier.height(8.dp))
         }

        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(42.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.LightGray)) {

                item?.accountType?.let { type ->
                    Text(text = "Type: $type")
                }
                Text(text = "Product name: ${state.accountDetails.productName ?: ""}")
                Text(text = "Opened date: ${state.accountDetails.openedDate ?: ""}")
                Text(text = "Branch: ${state.accountDetails.branch ?: ""}")
                Text(text = "Beneficiaries: ${state.accountDetails.beneficiaries ?: ""}")

            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}