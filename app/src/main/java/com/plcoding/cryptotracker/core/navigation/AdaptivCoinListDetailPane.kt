@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.plcoding.cryptotracker.core.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListActions
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListScreen
import com.plcoding.cryptotracker.crypto.presentation.coinList.CoinListViewModel
import com.plcoding.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreen(
                    coinListState = state.value,
                    events = viewModel.event,
                    onActions = { action ->
                        viewModel.onAction(action)
                        when (action) {
                            is CoinListActions.OnCoinClick -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }
                        }
                    },
                    modifier = modifier
                )
            }

        },
        detailPane = {
            CoinDetailScreen(
                state = state.value,
                modifier = modifier
            )
        }
    )

}