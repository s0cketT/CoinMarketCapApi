package com.assistant.cryptoapi.domain.use_case.get_coin

import android.util.Log
import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.data.repository.CoinRepository
import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinDetail
import com.assistant.cryptoapi.domain.model.CoinDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetCoinByIdUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(id: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading())
            val coinInfoResponse = coinRepository.getCoinsById(id)

            // Получаем CoinDetails из Map
            val coinDetails = coinInfoResponse.data[id] // Здесь мы получаем CoinDetails по ID

            // Проверяем, найден ли CoinDetails
            if (coinDetails != null) {
                emit(Resource.Success(coinDetails))
            } else {
                emit(Resource.Error("Coin not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}
