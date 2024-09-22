package com.example.financedroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.financedroid.ui.theme.FinanceDroidTheme
import com.example.financedroid.screens.OverViewScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceDroidTheme {
                OverViewScreen()
            }
        }
    }
}


