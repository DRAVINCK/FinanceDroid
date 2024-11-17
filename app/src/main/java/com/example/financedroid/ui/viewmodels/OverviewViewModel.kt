package com.example.financedroid.ui.viewmodels

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.financedroid.data.models.Transaction
import com.example.financedroid.data.repositories.DummyRepository
import com.example.financedroid.data.repositories.IFinanceRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.math.BigDecimal


class OverviewViewModel(
    private val repository: IFinanceRepository = DummyRepository
) : ViewModel() {



    private val _transaction = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> get() = _transaction

    private val filter = MutableStateFlow<String?>(null)
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            combine(transactions, filter){ transactions, filter ->
                UiState(transactions = transactions)
            }.collectLatest {
                _uiState.value = it
            }
        }
        viewModelScope.launch {
            repository.getTransactions().collectLatest { lista ->
                _transaction.value = lista
            }
        }
    }

    fun getTransactions() {
        viewModelScope.launch {
            repository.getTransactions().collectLatest { lista ->
                _transaction.value = lista
            }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            repository.addTransaction(transaction)
        }
    }



    fun clearTransactions() {
        viewModelScope.launch {
            repository.clearTransactions()
        }
    }

    fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch {
            repository.updateTransaction(transaction)
        }
    }

    fun deleteTransaction(uuid: String) {
        viewModelScope.launch {
            repository.deleteTransaction(uuid)
        }
    }

    suspend fun findTransaction(uuid: String) = repository.findTransaction(uuid)

    suspend fun filterByCategory(category: String) {
        filter.value = category
    }

    suspend fun clearFilter() {
        filter.value = null
    }



    data class UiState(
        val advice: String = "",
        val userName: String = "",
        val transactions: List<Transaction> = emptyList(),
        val total: Double = transactions.sumOf { it.value }

    )

}