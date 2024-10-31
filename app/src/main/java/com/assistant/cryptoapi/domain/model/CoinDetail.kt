package com.assistant.cryptoapi.domain.model

import com.google.gson.annotations.SerializedName

data class CoinDetail(
    val id: String,
    val name: String,
    val symbol: String,
    val logo: String,
    val rank: Int,
    val description: String,
)


data class CoinInfoResponse(
    val data: Map<String, CoinDetails> // Если API возвращает данные в этом формате
)


data class CoinDetails(
    val id: String,
    val name: String,
    val symbol: String,
    val logo: String,
    val description: String,
    @SerializedName("tag-names")
    val tagNames: List<String>,
    val urls: URL,
    // другие поля
)

data class URL(
    val website: List<String>,
    @SerializedName("source_code")
    val gitHub: List<String>,
    @SerializedName("announcement")
    val documentation: List<String>,

    val twitter: List<String>,
    val message_board: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
)