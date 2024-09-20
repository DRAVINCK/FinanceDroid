package com.example.financedroid.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.sp
import com.example.financedroid.OverViewScreen
import com.example.financedroid.OverviewViewModel


@Composable
fun Tela2(
    it: PaddingValues,
    uiState: OverviewViewModel.UiState,
    ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "TELA 2", fontSize = 24.sp)
    }
}



@Preview
@Composable
fun OverViewScreenPreview() {
    OverViewScreen()
}