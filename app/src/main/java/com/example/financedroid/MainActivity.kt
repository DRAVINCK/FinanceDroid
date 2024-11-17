package com.example.financedroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.example.financedroid.data.db.openDatabase
import com.example.financedroid.data.repositories.IFinanceRepository
import com.example.financedroid.data.repositories.LocalRepositoryTransaction
import com.example.financedroid.data.repositories.RemoteRepositoryTransaction
import com.example.financedroid.ui.viewmodels.OverviewViewModel
import com.example.financedroid.views.navegation.FinanceDroidNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isLocal = false
            val repository: IFinanceRepository
            if (isLocal){
                val db = remember {openDatabase(this)}
                val dao = db.transactionDao()
                repository = LocalRepositoryTransaction(dao)
            }else {
                repository = RemoteRepositoryTransaction()
            }
            val viewModel = OverviewViewModel(repository)
            FinanceDroidNavHost(viewModel)

        }
    }
}




