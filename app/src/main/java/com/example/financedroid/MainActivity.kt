package com.example.financedroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.financedroid.ui.theme.FinanceDroidTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewmodel.compose.viewModel

class MyviewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    //funçao para add uma nova transação
    fun add(transaction: String) {
        val currentTransactions = _uiState.value.transactions.toMutableList()
        currentTransactions.add(transaction)
        _uiState.value = UiState(transactions = currentTransactions)
    }
    data class UiState(
        val transactions: List<String> = emptyList()
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceDroidTheme {
                Column {
                    Welcome()
                    Transactions()
                }
            }
        }
    }
}

@Composable
fun Welcome(){
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "seja bem vindo, \nLucas" ,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Limpar Transações.",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(27.dp)
        )
    }
}

@Composable
fun Transactions(viewModel: MyviewModel = viewModel()){
    val uiState by viewModel.uiState.collectAsState() // transforma o flow em um estado do jetpack compose

    Column{
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
        ) {
            // HEADER DA LISTA
            item{
                Text(
                    text = "Transações",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            // LISTA DE TRANSACOES
            items(uiState.transactions.size){index ->
                Transaciton(uiState.transactions[index])
            }
        }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { viewModel.add("Nova Transação") },
                )
                {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Botão Redondo",
                        tint = Color.White
                    )
                }
            }

    }
}
@Composable
private fun Transaciton(transaction: String){

    // CARD
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {

            Icon(imageVector = Icons.Filled.Settings,
                contentDescription = "Settings",
                modifier = Modifier.padding(8.dp)
                )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = transaction,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }

    }
}
