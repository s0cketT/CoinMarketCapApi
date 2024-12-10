package com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail

import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinDetail
import com.assistant.cryptoapi.domain.model.CoinDetails

data class CoinDetailState(
    val coin: CoinDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
