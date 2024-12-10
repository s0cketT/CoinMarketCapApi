package com.assistant.cryptoapi.domain.repository

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.assistant.cryptoapi.data.remote.CoinPaprikaApi
import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinInfoResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {

    var mail: String? = null
    var pas: String? = null

    var money: Double? = null

}