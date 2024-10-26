package com.plcoding.cryptotracker.crypto.domain

data class Coin(
    val id: Int,
    val name: String,
    val rank: Int,
    val symbol: Int,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24H: Double
)
