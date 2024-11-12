package com.example.financedroid.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financedroid.data.repositories.DummyRepository
import com.example.financedroid.data.models.Transaction
import com.example.financedroid.data.repositories.IFinanceRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.math.BigDecimal


class OverviewViewModel(
    private val repository: IFinanceRepository
) : ViewModel() {

    private val _transaction = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> get() = _transaction

    private val filter = MutableStateFlow<String?>(null)
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getTransactions().collectLatest { lista ->
                _transaction.value = lista
            }
        }
    }

    //funçao para add uma nova transação
    suspend fun addTransaction(transaction: Transaction) {
        repository.addTransaction(transaction)
        updateUiState()
    }

    suspend fun clearTransactions() {
        repository.clearTransactions()
        updateUiState()
    }

    suspend fun updateTransaction(transaction: Transaction) {
        repository.updateTransaction(transaction)
        updateUiState()
    }

    suspend fun deleteTransaction(uuid: String) {
        repository.deleteTransaction(uuid)
        updateUiState()
    }

    suspend fun findTransaction(uuid: String) = repository.findTransaction(uuid)

    suspend fun filterByCategory(category: String) {
        filter.value = category
        updateUiState()
    }

    suspend fun clearFilter() {
        filter.value = null
        updateUiState()
    }

    private suspend fun updateUiState(){
        val transactionListSaved = repository.transactions
        val transactions = if (filter.value != null){
            transactionListSaved.filter { it.category == filter.value }
        } else {
            transactionListSaved
        }
        _uiState.value = uiState.value.copy(
            transactions = transactions,
            total = transactions.sumOf { it.value }
        )
    }

    data class UiState(
        val advice: String = "",
        val userName: String = "",
        val transactions: List<Transaction> = emptyList(),
        val total: BigDecimal = transactions.sumOf { it.value }

    )

}