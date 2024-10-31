package com.assistant.cryptoapi.domain.use_case.get_exchange_by_id

import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.data.repository.CoinRepository
import com.assistant.cryptoapi.domain.model.CoinDetails
import com.assistant.cryptoapi.domain.model.ExchangeDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetExchangeByIdUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    operator fun invoke(id: String): Flow<Resource<ExchangeDetail>> = flow {
        try {
            emit(Resource.Loading())
            val exchangeInfoResponse = coinRepository.getExchangeById(id)

            // Получаем CoinDetails из Map
            val exchangeDetails = exchangeInfoResponse.data[id] // Здесь мы получаем CoinDetails по ID

            // Проверяем, найден ли CoinDetails
            if (exchangeDetails != null) {
                emit(Resource.Success(exchangeDetails))
            } else {
                emit(Resource.Error("Coin not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}
