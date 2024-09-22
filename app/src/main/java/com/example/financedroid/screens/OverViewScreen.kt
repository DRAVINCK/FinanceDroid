package com.example.financedroid.screens

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financedroid.Utils.TopbarAItip
import com.example.financedroid.Utils.TopbarInicial
import com.example.financedroid.Utils.randomTransaction
import androidx.compose.material3.Icon
import com.example.financedroid.OverviewViewModel
import com.example.financedroid.Utils.TopbarInfoValues


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OverViewScreen(viewModel: OverviewViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            when (selectedItem) {
                0 -> TopbarInicial()
                1 -> TopbarInfoValues()
                2 -> TopbarAItip()
                else -> TopbarInicial()
            }
        },
        floatingActionButton = {
            if (selectedItem == 0) {
                FloatingActionButton(onClick = {
                    viewModel.addTransaction(randomTransaction())
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Adicionar Transação"
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Edit, contentDescription = "Tela 1") },
                    label = { Text("Transações") },
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Search, contentDescription = "Tela 2") },
                    label = { Text("Totais") },
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Info, contentDescription = "Tela 3") },
                    label = { Text("Dicas") },
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 }
                )
            }
        }
    ) {
        when (selectedItem) {
            0 -> TransactionScreen(it, uiState)
            1 -> TotalInfoScreen(it, uiState)
            2 -> AdviceScreen(it, uiState)
        }
    }
}


@Preview
@Composable
fun OverViewScreenPreview() {
    OverViewScreen()
}

