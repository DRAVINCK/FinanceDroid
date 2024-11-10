package com.example.financedroid.views.navegation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financedroid.ui.viewmodels.OverviewViewModel
import com.example.financedroid.views.screens.LoginScreen
import com.example.financedroid.views.screens.NewTransactionScreen
import com.example.financedroid.views.screens.OverViewScreen
import com.example.financedroid.views.screens.RegisterScreen
import com.example.financedroid.views.screens.Screen

@Composable
fun FinanceDroidNavHost(
    viewModel: OverviewViewModel = viewModel()
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Overview.route) {
        composable(Screen.Overview.route) { OverViewScreen(navController, viewModel) }
        composable(Screen.NewTransaction.route) { NewTransactionScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Register.route) { RegisterScreen(navController) }

    }
}