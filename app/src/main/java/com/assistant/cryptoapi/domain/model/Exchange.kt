package com.assistant.cryptoapi.domain.model

data class Exchange(
    val data: List<ExchangeListResponse>
)

data class ExchangeListResponse(
    val id: String,
    val name: String
)