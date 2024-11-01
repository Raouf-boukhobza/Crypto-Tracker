package com.plcoding.cryptotracker.crypto.data.networking.mappers

import com.plcoding.cryptotracker.crypto.data.networking.dtos.CoinDto
import com.plcoding.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin()  : Coin{
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24H = changePercent24Hr
    )

}