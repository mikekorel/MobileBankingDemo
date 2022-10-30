package com.mikekorel.mobilebankingdemo.presentation

sealed class Screen(val route: String) {
    object AccountsListScreen: Screen("accounts_list_screen")
    object AccountDetailsScreen: Screen("account_details_screen")
}
