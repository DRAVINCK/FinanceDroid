package com.example.financedroid.ui.theme


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.financedroid.R
import com.example.financedroid.data.Transaction
import java.math.BigDecimal
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random


fun Date.formatDate(): String {
    val dateFormat = SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())
    return dateFormat.format(this)

}


fun BigDecimal.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return numberFormat.format(this)
}

val categories = listOf(
    "Comida" to Icons.Default.FavoriteBorder,
    "Transporte" to Icons.Default.Build,
    "Shopping" to Icons.Default.ShoppingCart,
    "Saúde" to Icons.Default.Favorite,
    "Entretenimento" to Icons.Default.Face,
    "Utilitários" to Icons.Default.Build,
)

fun randomTransaction() = Transaction(
    category = categories.random().first,
    value = BigDecimal.valueOf( Random.nextDouble() / Random.nextDouble())
)