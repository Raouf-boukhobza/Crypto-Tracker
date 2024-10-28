package com.plcoding.cryptotracker.crypto.presentation.models

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.util.getDrawableIdForCoin
import java.util.Locale

data class CoinUi(

    val id: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24H: DisplayableNumber,
    @DrawableRes val iconRes: Int
)


data class DisplayableNumber(
    val value: Double,
    val format: String
)


fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        rank = rank,
        symbol = symbol,
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24H = changePercent24H.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}


fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        format = formatter.format(this)
    )
}