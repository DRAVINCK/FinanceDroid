package com.example.financedroid.Utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.math.BigDecimal
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.ContentAlpha
import com.example.financedroid.OverviewViewModel

@Composable
fun TransactionCard(
    uui: String,
    category: String,
    value: BigDecimal,
    date: String,
    deletar: () -> Unit,


    ){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            contentColor = MaterialTheme.colorScheme.onSurface

        )
    ) {
        TransactionInfoRow(deletar, uui, category,date, value)
    }
}

@Composable
private fun TransactionInfoRow( deletar: () -> Unit, uui: String, category: String, date: String, value: BigDecimal) {
    Column(Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(imageVector = (categories.firstOrNull {it.first == category }?.second ?: Icons.Default.Info),
                contentDescription = "Food")
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = category, style = MaterialTheme.typography.titleMedium)
                Text(text = date, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium))
            }
            Spacer(modifier = Modifier.width(16.dp))

            Text(text = value.toCurrency(),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))

            IconButton(onClick = {deletar }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Food", )
            }


        }
    }
}

@Preview
@Composable
fun TransactionCardPreview() {
    TransactionCard(
        uui = "",
        category = "Food",
        value = BigDecimal.valueOf(10.00),
        date = "set. 15",
        deletar = {}
    )
}
