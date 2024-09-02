package com.example.financedroid

import android.os.Bundle
import android.view.SurfaceControl.Transaction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financedroid.ui.theme.FinanceDroidTheme

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

private val transactionDummy = listOf<String>(
    "Gasolina", "Aluguel", "Estudos", "Role",
    "Gasolina", "Aluguel", "Estudos", "Role",
    "Gasolina", "Aluguel", "Estudos", "Role",
    "Gasolina", "Aluguel", "Estudos", "Role",
    "Gasolina", "Aluguel", "Estudos", "Role",
    "Gasolina", "Aluguel", "Estudos", "Role",
    "Gasolina", "Aluguel", "Estudos", "Role",

)

@Composable
fun Transactions(){
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(transactionDummy.size){index ->
            Text(text = transactionDummy[index])
        }
    }
    Column(
        modifier = Modifier.padding(16.dp)
    )   {
        transactionDummy.forEach { transaction ->
            Text(text = transaction)

        }


    }

}

