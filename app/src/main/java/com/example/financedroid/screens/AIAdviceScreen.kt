package com.example.financedroid.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financedroid.OverviewViewModel
import com.example.financedroid.Utils.RandomInvestmentTip

// testar como consumir api e animacao de clique https://platform.openai.com/docs/guides/completions
@Composable
fun AdviceScreen(
    it: PaddingValues,
    uiState: OverviewViewModel.UiState,
    ) {
    var isVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(it),
        verticalArrangement = Arrangement.Center,

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isVisible = !isVisible }
                .padding(16.dp),
        ) {
                Text(
                    text = "Dicas diárias",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineSmall
                )
                Icon(
                    imageVector = if(isVisible){
                        Icons.Default.KeyboardArrowDown
                    } else Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    tint = Color.Gray
                )
        }
        // animacao de aparecer e desaparecer quando clicado na linha
        AnimatedVisibility(visible = isVisible) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                text = RandomInvestmentTip().text,
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}



@Preview()
@Composable
fun AdviceScreenPreview() {
    AdviceScreen(
        it = PaddingValues(),
        uiState = OverviewViewModel.UiState()
    )
}