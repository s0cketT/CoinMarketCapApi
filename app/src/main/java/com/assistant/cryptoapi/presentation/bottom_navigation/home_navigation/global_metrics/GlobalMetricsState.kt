package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.global_metrics

import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinDetail
import com.assistant.cryptoapi.domain.model.GlobalMetrics

data class GlobalMetricsState(
    val isLoading: Boolean = false,
    val coin: GlobalMetrics? = null,
    val error: String = ""
)
