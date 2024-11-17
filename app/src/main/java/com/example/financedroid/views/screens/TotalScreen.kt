package com.example.financedroid.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financedroid.ui.viewmodels.OverviewViewModel
import com.example.financedroid.ui.utils.toCurrency
import java.math.BigDecimal


@Composable
fun TotalInfoScreen(
    it: PaddingValues,
    uiState: OverviewViewModel.UiState,
) {
    Column(modifier = Modifier.padding(it),
        verticalArrangement = Arrangement.Center)
    {
        TotalCard("Total de transações",uiState.total.toCurrency())
        TotalCard("Quantidade de transações",uiState.transactions.size.toString())

    }

}




@Composable
fun TotalCard(
    titulo: String,
    value: String
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(150.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            contentColor = MaterialTheme.colorScheme.onSurface

        )
    ) {
        TotalInfoColumn(titulo, value)
    }
}

@Composable
fun TotalInfoColumn(
    titulo: String,
    valueTotal: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(text = titulo)

        Text(
            text = valueTotal,
            style = MaterialTheme.typography.displayMedium,
            modifier= Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),

        )
    }


}

@Preview
@Composable
fun TotalnCardPreview() {
    TotalCard(
        titulo = "teste titulo",
        value = BigDecimal.valueOf(10.00).toDouble().toCurrency()
    )
}

@Preview
@Composable
fun Tela3Preview() {
    TotalInfoScreen(
        it = PaddingValues(),
        uiState = OverviewViewModel.UiState()
    )

}