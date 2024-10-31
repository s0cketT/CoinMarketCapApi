package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list

import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinListResponse

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinListResponse> = emptyList(),
    val error: String = ""
)
