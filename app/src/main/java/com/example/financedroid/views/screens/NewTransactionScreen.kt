package com.example.financedroid.views.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.financedroid.data.models.Transaction
import com.example.financedroid.ui.viewmodels.OverviewViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.financedroid.ui.utils.categoriesList
import java.util.Date
import androidx.compose.material3.OutlinedTextField as OutlinedTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTransactionScreen(
    navController: NavHostController,
    viewModel: OverviewViewModel = viewModel()
) {
    var value by remember { mutableStateOf("") } // Estado para o valor da transação
    var selectedCategory by remember { mutableStateOf("") } // Estado da categoria
    var showCategoryList by remember { mutableStateOf(false) } // Controla a exibição da lista de categorias


    Column(
        modifier = Modifier
            .fillMaxSize() // Preencher a tela inteira
            .wrapContentSize(Alignment.Center)
    ) {
        // Campo para o valor da transação
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            label = { Text("Valor") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        // Campo de categoria
        OutlinedTextField(
            value = selectedCategory,
            onValueChange = { },
            label = { Text("Categoria") },
            readOnly = true, // Torna o campo somente leitura
            trailingIcon = {
                IconButton(onClick = { showCategoryList = !showCategoryList }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Toggle categories list")
                }
            }
        )

        // Lista de categorias, visível apenas quando showCategoryList é true
        if (showCategoryList) {
            Column(modifier = Modifier.fillMaxWidth()) {
                categoriesList.forEach { category ->
                    TextButton(
                        onClick = {
                            selectedCategory = category.name
                            showCategoryList = false // Fecha a lista ao selecionar a categoria
                        },
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        Text(category.name)
                    }
                }
            }
        }

        // Botão para salvar a transação
        Button(onClick = {
            if (value.isNotBlank() && selectedCategory.isNotBlank()) {
                val transaction = Transaction(
                    category = selectedCategory,
                    value = value.toDouble(),
                    date = Date()
                )

                viewModel.addTransaction(transaction) // Adiciona a transação no ViewModel
                navController.popBackStack() // Volta para a tela anterior
            } else {
                // Exibe um aviso se os campos não estiverem preenchidos corretamente
                Toast.makeText(navController.context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Adicionar Transação")
        }
    }
}