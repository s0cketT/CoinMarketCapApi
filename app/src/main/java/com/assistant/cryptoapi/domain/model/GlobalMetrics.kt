package com.assistant.cryptoapi.domain.model

data class GlobalMetrics(
    val data: Data
)

data class Data(
    val btc_dominance: Double,
    val eth_dominance: Double,
    val quote: GlobalQuote
)

data class GlobalQuote(
    val USD: GlobalUSDData
)

data class GlobalUSDData(
    val total_market_cap: Double,
    val total_volume_24h: Double,
    val total_market_cap_yesterday_percentage_change: Double,
    val total_volume_24h_yesterday_percentage_change: Double,
)