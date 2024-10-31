package com.assistant.cryptoapi.domain.model


data class Coin(
    val data: List<CoinListResponse>
)

data class CoinListResponse(
    val id: String,
    val name: String,
    val symbol: String,
    val cmc_rank: String,
    val quote: Quote
)

data class Quote(
    val USD: USDData
)

data class USDData(
    val market_cap: Double,
    val price: String,
    val percent_change_24h: Double,
    val percent_change_1h: Double,
    val percent_change_7d: Double,
    val percent_change_30d: Double
)