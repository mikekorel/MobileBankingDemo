package com.mikekorel.mobilebankingdemo.presentation.accountdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mikekorel.mobilebankingdemo.R
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import com.mikekorel.mobilebankingdemo.presentation.accountdetails.AccountDetailsViewModel
import com.mikekorel.mobilebankingdemo.presentation.accountslist.components.AccountsListItem

@Composable
fun AccountDetailsScreen(
    item: AccountsListItem? = null,
    viewModel: AccountDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val transactionItems = viewModel.transactions?.collectAsLazyPagingItems()

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

        transactionItems?.let { items ->
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
            ) {
                items(items) { item ->
                    item?.let { transaction ->
                        Text(text = "ID = ${transaction.id}")
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
        transactionItems?.apply {
            if (loadState.prepend is LoadState.Error || loadState.append is LoadState.Error ||
                loadState.refresh is LoadState.Error
            ) {
                Text(text = stringResource(R.string.transactions_error_msg), color = Color.Red)
            }
        }
    }
}