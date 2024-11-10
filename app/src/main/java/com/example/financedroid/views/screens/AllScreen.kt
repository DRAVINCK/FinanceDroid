package com.example.financedroid.views.screens

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Overview : Screen("overview")
    object Register : Screen("register")
    object NewTransaction : Screen("new_transaction")

    // ... outras telas, como Transaction, TotalInfo, Advice ...
}