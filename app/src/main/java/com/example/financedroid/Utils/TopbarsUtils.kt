package com.example.financedroid.Utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopbarInicial() {
        TopAppBar(
            modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
            title = {
                Text(
                    text = "Bem vindo, \nLucas Teixeira",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar2() {
    TopAppBar(
        modifier = Modifier.padding(bottom = 32.dp),
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        title = {
            Text(
                text = "Tela 2",
                style = MaterialTheme.typography.headlineMedium
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Limpar Transações",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar3() {
    TopAppBar(
        modifier = Modifier.padding( bottom = 32.dp),
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        title = {
            Text(
                text = "Tela 3",
                style = MaterialTheme.typography.headlineMedium
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Limpar Transações",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}


@Preview
@Composable
fun TopbarInicialPreview() {
    Topbar3()
}