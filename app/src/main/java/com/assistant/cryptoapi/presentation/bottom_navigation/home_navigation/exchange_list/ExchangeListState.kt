package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list

import com.assistant.cryptoapi.domain.model.CoinListResponse
import com.assistant.cryptoapi.domain.model.ExchangeListResponse

data class ExchangeListState(
    val isLoading: Boolean = false,
    val exchanges: List<ExchangeListResponse> = emptyList(),
    val error: String = ""
)
