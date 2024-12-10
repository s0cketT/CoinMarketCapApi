package com.assistant.cryptoapi.presentation.bottom_navigation.exchange_detail

import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinDetail
import com.assistant.cryptoapi.domain.model.CoinDetails
import com.assistant.cryptoapi.domain.model.ExchangeDetail

data class ExchangeDetailState(
    val exchange: ExchangeDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
