package com.plcoding.cryptotracker.crypto.data.networking.dtos

import kotlinx.serialization.Serializable


@Serializable
data class CoinHistoryDto(
    val data : List<CoinPriceDto>
)
