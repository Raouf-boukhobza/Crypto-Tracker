package com.plcoding.cryptotracker.crypto.data.networking.mappers

import com.plcoding.cryptotracker.crypto.data.networking.dtos.CoinPriceDto
import com.plcoding.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinPriceDto.toCoinPrice() : CoinPrice{
    return CoinPrice(
        priceUsd = priceUsd,
        time = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.of("UTC"))
    )

}