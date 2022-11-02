package com.mikekorel.mobilebankingdemo.presentation.accountdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mikekorel.mobilebankingdemo.core.Constants
import com.mikekorel.mobilebankingdemo.core.Resource
import com.mikekorel.mobilebankingdemo.domain.model.AccountDetails
import com.mikekorel.mobilebankingdemo.domain.model.Transaction
import com.mikekorel.mobilebankingdemo.domain.usecase.GetAccountDetailsUseCase
import com.mikekorel.mobilebankingdemo.domain.usecase.GetAccountsListUseCase
import com.mikekorel.mobilebankingdemo.domain.usecase.GetTransactionHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountDetailsViewModel @Inject constructor(
    private val getAccountDetailsUseCase: GetAccountDetailsUseCase,
    private val getTransactionHistoryUseCase: GetTransactionHistoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AccountDetailsState())
    val state: State<AccountDetailsState> = _state

    var transactions: Flow<PagingData<Transaction>>? = null

    init {
        savedStateHandle.get<String>(Constants.PARAM_ACCOUNT_ID)?.let { accountId ->
            getAccountsDetails(accountId)
            transactions = getTransactionHistory(accountId)
        }
    }

    private fun getAccountsDetails(accountId: String) {
        getAccountDetailsUseCase(accountId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AccountDetailsState(
                        accountDetails = result.data ?: AccountDetails()
                    )
                }
                is Resource.Error -> {
                    _state.value = AccountDetailsState(
                        error = result.message ?: "An unexpected error occurred!"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AccountDetailsState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTransactionHistory(accountId: String) =
        getTransactionHistoryUseCase(accountId).cachedIn(viewModelScope)

}