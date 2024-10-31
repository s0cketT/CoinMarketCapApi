package com.assistant.cryptoapi.data.repository

import android.util.Log
import com.assistant.cryptoapi.data.remote.CoinPaprikaApi
import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinInfoResponse
import javax.inject.Inject

class CoinRepository @Inject constructor(private val coinApi: CoinPaprikaApi) {
    suspend fun getCoins(limit : Int) = coinApi.getCoins(limit)
    suspend fun getCoinsById(coinId: String)  = coinApi.getCoinById(coinId)

    suspend fun getExchanges(limit : Int, sort: String) = coinApi.getExchanges(limit, sort)
    suspend fun getExchangeById(exchangeId: String) = coinApi.getExchangeById(exchangeId)



    suspend fun getGlobalMetrics() = coinApi.getGlobalMetrics()
}