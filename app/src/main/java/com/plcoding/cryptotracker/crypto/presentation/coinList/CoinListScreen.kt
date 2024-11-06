package com.plcoding.cryptotracker.crypto.presentation.coinList

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.plcoding.cryptotracker.core.presentation.util.toString
import com.plcoding.cryptotracker.crypto.presentation.coinList.components.CoinListItem
import com.plcoding.cryptotracker.crypto.presentation.coinList.components.PreviewCoin
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CoinListScreen(
    coinListState : CoinListState,
    events : Flow<CoinListEvent>,
    onActions : (CoinListActions) -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
            events.collect{ event ->
                when(event){
                    is CoinListEvent.Error -> {
                        Toast.makeText(
                            context,
                            event.error.toString(context = context),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
    if (coinListState.isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(coinListState.coins){coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = {
                        onActions(CoinListActions.OnCoinClick(coinUi))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewScreen() {
    CryptoTrackerTheme {
        CoinListScreen(
            coinListState = CoinListState(
                coins = (1..10).map {
                    PreviewCoin.copy(id = it.toString())
                }
            ),
            events = emptyFlow(),
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            ),
            onActions = {}
        )
    }

}