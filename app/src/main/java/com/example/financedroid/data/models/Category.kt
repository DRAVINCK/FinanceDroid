package com.example.financedroid.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val name: String,
    val icon: ImageVector
)

val categoriesList = listOf(
    Category("Comida", Icons.Filled.FavoriteBorder),
    Category("Transporte", Icons.Filled.Build),
    Category("Shopping", Icons.Filled.ShoppingCart),
    Category("Saúde", Icons.Filled.Favorite),
    Category("Entretenimento", Icons.Filled.Face),
    Category("Utilitários", Icons.Filled.Build)
)
