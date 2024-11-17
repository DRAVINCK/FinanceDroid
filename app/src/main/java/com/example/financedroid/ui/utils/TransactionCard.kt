package com.example.financedroid.ui.utils

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


@Composable
fun TransactionCard(
    uuid: String,
    category: String,
    value: Double,
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
        TransactionInfoRow(
            deletar = deletar,
            uuid = uuid,
            category = category,
            date = date,
            value = value.toBigDecimal()
        )
    }
}

@Composable
private fun TransactionInfoRow(
    deletar: () -> Unit,
    uuid: String,
    category: String,
    date: String,
    value: BigDecimal
) {
    Column(Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Encontrar o ícone da categoria com base no nome
            val categoryIcon = categoriesList.firstOrNull { it.name == category }?.icon
                ?: Icons.Filled.Info  // Usar um ícone padrão caso não encontre a categoria

            // Exibir o ícone da categoria
            Icon(
                imageVector = categoryIcon,
                contentDescription = category
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Exibir o nome da categoria e a data
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = category, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Exibir o valor da transação
            Text(
                text = value.toPlainString(),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))

            // Botão de deletar
            IconButton(onClick = { deletar() }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview
@Composable
fun TransactionCardPreview() {
    TransactionCard(
        uuid = "",
        category = "Food",
        value = BigDecimal.valueOf(10.00).toDouble(),
        date = "set. 15",
        deletar = {}
    )
}
