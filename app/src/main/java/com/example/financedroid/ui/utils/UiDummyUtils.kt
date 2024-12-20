package com.example.financedroid.ui.utils


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.financedroid.data.models.InvestmentTip
import com.example.financedroid.data.models.InvestmentTips
import com.example.financedroid.data.models.Transaction
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


fun Double.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return numberFormat.format(this)
}

val categoriees = listOf(
    "Comida" to Icons.Default.FavoriteBorder,
    "Transporte" to Icons.Default.Build,
    "Shopping" to Icons.Default.ShoppingCart,
    "Saúde" to Icons.Default.Favorite,
    "Entretenimento" to Icons.Default.Face,
    "Utilitários" to Icons.Default.Build,
)



fun randomTransaction() = Transaction(
    category = categoriesList.random().name,
    value = BigDecimal.valueOf( Random.nextDouble() / Random.nextDouble()).toDouble()
)

fun RandomInvestmentTip(): InvestmentTip {
    return InvestmentTips.tips.random()
}

