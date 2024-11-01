package com.plcoding.cryptotracker.crypto.presentation.coinList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptotracker.core.domain.util.onError
import com.plcoding.cryptotracker.core.domain.util.onSuccess
import com.plcoding.cryptotracker.crypto.domain.CoinDataSource
import com.plcoding.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel (
    private val coinDataSource: CoinDataSource
): ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state.asStateFlow()

    init {
        loadCoins()
    }

    private fun loadCoins(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            coinDataSource
                .getCoin()
                .onSuccess {coin ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coin.map { it.toCoinUi() }
                        )
                    }
                }

                .onError {
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }


    fun onAction(action : CoinListActions){
        when(action) {
            is CoinListActions.OnCoinClick -> {

            }
        }
    }
}