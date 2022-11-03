package com.mikekorel.mobilebankingdemo.presentation.accountslist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mikekorel.mobilebankingdemo.presentation.Screen
import com.mikekorel.mobilebankingdemo.presentation.accountslist.AccountsListViewModel

@Composable
fun AccountsListScreen(
    navController: NavController,
    viewModel: AccountsListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            if (state.favoriteAccount != null) {
                item {
                    with(state.favoriteAccount) {
                        AccountsListItem(
                            account = this,
                            onItemClick = {
                                viewModel.selectedAccount = it
                                navController.navigate(Screen.AccountDetailsScreen.route + "/${this.id}")
                            },
                            onFavoriteClick = {
                                viewModel.clearFavorite()
                            },
                            isFavorite = true
                        )
                    }
                }
            }
            items(state.accounts.filterNot {
                it.id == state.favoriteAccount?.id && state.favoriteAccount?.id != null
            }) { account ->
                AccountsListItem(
                    account = account,
                    onItemClick = {
                        viewModel.selectedAccount = it
                        navController.navigate(Screen.AccountDetailsScreen.route + "/${account.id}")
                    },
                    onFavoriteClick = {
                        viewModel.markAsFavorite(it)
                    }
                )
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}