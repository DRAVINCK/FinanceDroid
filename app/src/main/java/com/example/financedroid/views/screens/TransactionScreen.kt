package com.example.financedroid.views.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.financedroid.ui.viewmodels.OverviewViewModel
import com.example.financedroid.ui.utils.TransactionCard
import com.example.financedroid.ui.utils.formatDate



@Composable
fun TransactionScreen(

    it: PaddingValues,
    uiState: OverviewViewModel.UiState,
    viewModel: OverviewViewModel
) {

    Column(modifier = Modifier.padding(it)) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.padding(start = 16.dp)
                    .weight(1f),
                text = "Transações",

            )
            IconButton(onClick = { viewModel.clearTransactions() }) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Limpar Transações",
                    tint = MaterialTheme.colorScheme.primary,

                )
            }

        }
        LazyColumn(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            ),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 16.dp
            )
        ) {
            items(uiState.transactions) { transaction ->
                TransactionCard(
                    uui = transaction.uuid,
                    category = transaction.category,
                    value = transaction.value,
                    date = transaction.date.formatDate(),
                    deletar = { viewModel.deleteTransaction(transaction.uuid) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun TransactionScreenPreview() {
    TransactionScreen(
        it = PaddingValues(),
        uiState = OverviewViewModel.UiState(),
        viewModel = viewModel()
    )
}

