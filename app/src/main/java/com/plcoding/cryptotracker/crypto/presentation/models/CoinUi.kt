package com.plcoding.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes

data class CoinUi(

    val id: Int,
    val name: String,
    val rank: Int,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24H: DisplayableNumber,
    @DrawableRes val iconRes : Int
)


data class DisplayableNumber(
    val value: Double,
    val format: String
)