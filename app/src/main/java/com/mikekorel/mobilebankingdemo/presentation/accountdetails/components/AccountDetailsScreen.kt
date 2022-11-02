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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mikekorel.mobilebankingdemo.R
import com.mikekorel.mobilebankingdemo.core.DatePattern
import com.mikekorel.mobilebankingdemo.core.capitalizeFirstChar
import com.mikekorel.mobilebankingdemo.core.getDateString
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import com.mikekorel.mobilebankingdemo.presentation.accountdetails.AccountDetailsViewModel
import com.mikekorel.mobilebankingdemo.presentation.accountslist.components.AccountsListItem
import com.mikekorel.mobilebankingdemo.presentation.components.BackButton
import com.mikekorel.mobilebankingdemo.presentation.model.TransactionListItem
import com.mikekorel.mobilebankingdemo.presentation.ui.theme.CardColorLightGray
import timber.log.Timber

@Composable
fun AccountDetailsScreen(
    accountItem: AccountsListItem? = null,
    navController: NavController,
    viewModel: AccountDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val transactionListItems = viewModel.transactionListItems?.collectAsLazyPagingItems()

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
        accountItem?.let { account ->
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
                accountItem?.accountType?.let { type ->
                    AccountDetailsItem(
                        labelText = "Type: ",
                        valueText = type.capitalizeFirstChar()
                    )
                }
                AccountDetailsItem(
                    labelText = "Product name: ",
                    valueText = state.accountDetails.productName
                )
                AccountDetailsItem(
                    labelText = "Opened date: ",
                    valueText = try {
                        state.accountDetails.openedDate?.let { timestamp ->
                            getDateString(timestamp, DatePattern.DAY_MONTH_YEAR)
                        } ?: ""
                    } catch (t: Throwable) {
                        Timber.e(t, "Error parsing timestamp!")
                        ""
                    }
                )
                AccountDetailsItem(
                    labelText = "Branch: ",
                    valueText = state.accountDetails.branch
                )
                AccountDetailsItem(
                    labelText = "Beneficiaries: ",
                    valueText = state.accountDetails.beneficiaries?.joinToString()
                )

            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        transactionListItems?.let { items ->
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
            ) {
                items(items) { item ->
                    item?.let { transactionListItem ->
                        if (transactionListItem is TransactionListItem.TransactionUIModel) {
                            TransactionItem(
                                transaction = transactionListItem,
                                currencyCode = accountItem?.currencyCode ?: ""
                            )
                        } else {
                            // separator
                            Text(
                                text = (transactionListItem as? TransactionListItem.TransactionSeparator)?.date ?: "",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp, bottom = 6.dp)
                            )
                        }
                    }
                }

                transactionListItems.apply {
                    if (loadState.prepend is LoadState.Error || loadState.append is LoadState.Error ||
                        loadState.refresh is LoadState.Error
                    ) {
                        item {
                            Text(
                                text = stringResource(R.string.transactions_error_msg),
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(12.dp)
                            )
                        }
                    }
                    if (loadState.prepend is LoadState.Loading || loadState.append is LoadState.Loading ||
                        loadState.refresh is LoadState.Loading
                    ) {
                        item {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .align(Center)
                                        .padding(vertical = 16.dp)
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}