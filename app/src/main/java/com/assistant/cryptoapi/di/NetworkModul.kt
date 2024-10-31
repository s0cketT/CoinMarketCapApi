package com.assistant.cryptoapi.di

import com.assistant.cryptoapi.common.Constants
import com.assistant.cryptoapi.data.remote.CoinPaprikaApi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCoinApi(retrofit: Retrofit): CoinPaprikaApi {
        return retrofit.create(CoinPaprikaApi::class.java)
    }
}