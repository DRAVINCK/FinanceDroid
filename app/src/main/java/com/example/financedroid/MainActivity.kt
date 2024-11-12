package com.example.financedroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.example.financedroid.data.db.openDatabase
import com.example.financedroid.data.repositories.DummyRepository
import com.example.financedroid.data.repositories.IFinanceRepository
import com.example.financedroid.data.repositories.LocalRepositoryTransaction
import com.example.financedroid.data.repositories.RemoteRepositoryTransactio
import com.example.financedroid.ui.theme.FinanceDroidTheme
import com.example.financedroid.views.navegation.FinanceDroidNavHost
import com.example.financedroid.views.screens.OverViewScreen


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
                repository = RemoteRepositoryTransactio()
            }
            FinanceDroidNavHost()

        }
    }
}




