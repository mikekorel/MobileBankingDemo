package com.mikekorel.mobilebankingdemo.presentation.accountdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mikekorel.mobilebankingdemo.R
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import com.mikekorel.mobilebankingdemo.presentation.accountdetails.AccountDetailsViewModel
import com.mikekorel.mobilebankingdemo.presentation.accountslist.components.AccountsListItem
import com.mikekorel.mobilebankingdemo.presentation.components.BackButton
import com.mikekorel.mobilebankingdemo.presentation.ui.theme.CardColorLightGray

@Composable
fun AccountDetailsScreen(
    item: AccountsListItem? = null,
    navController: NavController,
    viewModel: AccountDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val transactionItems = viewModel.transactions?.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        BackButton(
            modifier = Modifier
                .size(28.dp)
                .offset(16.dp, 16.dp)
                .clickable {
                    navController.navigateUp()
                }
        )
        Spacer(modifier = Modifier.height(6.dp))
        item?.let { account ->
            AccountsListItem(
                account = account,
                onItemClick = {},
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(CardColorLightGray)
                .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(14.dp))
                .padding(12.dp))
            {

                Spacer(modifier = Modifier.height(4.dp))
                item?.accountType?.let { type ->
                    AccountDetailsItem(labelText = "Type: ", valueText = type)
                }
                AccountDetailsItem(labelText = "Product name: ", valueText = state.accountDetails.productName)
                AccountDetailsItem(labelText = "Opened date: ", valueText = state.accountDetails.openedDate)
                AccountDetailsItem(labelText = "Branch: ", valueText = state.accountDetails.branch)
                AccountDetailsItem(labelText = "Beneficiaries: ", valueText = state.accountDetails.beneficiaries?.toString())

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