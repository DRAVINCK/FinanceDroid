package com.example.financedroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financedroid.data.DummyRepository
import com.example.financedroid.data.Transaction
import com.example.financedroid.network.AiService
import com.example.financedroid.network.OpenAIPrompt
import com.example.financedroid.network.RetrofitModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal


class OverviewViewModel(
    private val repository: DummyRepository = DummyRepository,
    private val aiService: AiService = RetrofitModule.provideAiService(),
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val filter = MutableStateFlow<String?>(null)
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState


    init {
        val prompt = "Give me a small personal finance advice in PT-BR"
        viewModelScope.launch(ioDispatcher) {
            //Requisicao para servidor
            val response = aiService.completions(OpenAIPrompt(prompt))

            //se tiver sucesso
            if (response.isSuccessful) {
                // pega o dado da resposta
                response.body()?.choices?.first()?.text?.let { advice ->
                    // atualiza a UI
                    _uiState.value = _uiState.value.copy(advice = advice.trim())
                }
            }
        }
    }

    //funçao para add uma nova transação
    fun addTransaction(transaction: Transaction) {
        repository.add(transaction)
        updateUiState()
    }

    fun clearTransactions() {
        repository.clearTransactions()
        updateUiState()
    }

    fun updateTransaction(transaction: Transaction) {
        repository.updateTransaction(transaction)
        updateUiState()
    }

    fun deleteTransaction(uuid: String) {
        repository.deleteTransaction(uuid)
        updateUiState()
    }

    fun findTransaction(uuid: String) = repository.findTransaction(uuid)

    fun filterByCategory(category: String) {
        filter.value = category
        updateUiState()
    }

    fun clearFilter() {
        filter.value = null
        updateUiState()
    }

    private fun updateUiState(){
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