package com.mikekorel.mobilebankingdemo.presentation.accountslist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikekorel.mobilebankingdemo.core.Resource
import com.mikekorel.mobilebankingdemo.domain.model.AccountsListItem
import com.mikekorel.mobilebankingdemo.domain.usecase.GetAccountsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountsListViewModel @Inject constructor(
    private val getAccountsListUseCase: GetAccountsListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AccountsListState())
    val state: State<AccountsListState> = _state

    var selectedAccount: AccountsListItem? = null

    init {
        getAccountsList()
    }

    private fun getAccountsList() {
        getAccountsListUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = AccountsListState(
                        accounts = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = AccountsListState(
                        error = result.message ?: "An unexpected error occurred!"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AccountsListState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}