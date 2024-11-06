package com.plcoding.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListScreen
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListViewModel
import com.plcoding.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    when {
                        state.selectedCoin != null -> CoinDetailScreen(
                            state = state,
                            modifier = Modifier.padding(innerPadding))
                        else -> CoinListScreen(
                            coinListState = state,
                            events = viewModel.event,
                            onActions = viewModel::onAction,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                }
            }
        }
    }
}
