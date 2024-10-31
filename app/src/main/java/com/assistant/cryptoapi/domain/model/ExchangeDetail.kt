package com.assistant.cryptoapi.domain.model

import com.google.gson.annotations.SerializedName

data class ExchangeInfoResponse(
    val data: Map<String, ExchangeDetail>
)

data class ExchangeDetail(
    val id: String,
    val name: String,
    val logo: String,
    val description: String,
    val urls: URLEXCHANGE,
)


data class URLEXCHANGE(
    val website: List<String>,
    val twitter: List<String>,
    val chat: List<String>,
)