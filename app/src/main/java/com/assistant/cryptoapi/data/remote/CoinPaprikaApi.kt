package com.assistant.cryptoapi.data.remote

import com.assistant.cryptoapi.common.Constants
import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinDetail
import com.assistant.cryptoapi.domain.model.CoinInfoResponse
import com.assistant.cryptoapi.domain.model.Exchange
import com.assistant.cryptoapi.domain.model.ExchangeInfoResponse
import com.assistant.cryptoapi.domain.model.GlobalMetrics
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinPaprikaApi {

    @GET("cryptocurrency/listings/latest")
    suspend fun getCoins(
        @Query("limit") limit : Int,
        @Query("CMC_PRO_API_KEY") api: String = Constants.API_KEY): Coin

    @GET("exchange/map")
    suspend fun getExchanges(
        @Query("limit") limit : Int,
        @Query("sort") sort : String,
        @Query("CMC_PRO_API_KEY") api: String = Constants.API_KEY): Exchange

   /* @GET("coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetail*/

    @GET("cryptocurrency/info")
    suspend fun getCoinById(
        @Query("id") coinId : String,
        @Query("CMC_PRO_API_KEY") api: String = Constants.API_KEY
    ): CoinInfoResponse

    @GET("exchange/info")
    suspend fun getExchangeById(
        @Query("id") coinId : String,
        @Query("CMC_PRO_API_KEY") api: String = Constants.API_KEY
    ): ExchangeInfoResponse

    @GET("global-metrics/quotes/latest")
    suspend fun getGlobalMetrics(@Query("CMC_PRO_API_KEY") api: String = Constants.API_KEY) : GlobalMetrics
}