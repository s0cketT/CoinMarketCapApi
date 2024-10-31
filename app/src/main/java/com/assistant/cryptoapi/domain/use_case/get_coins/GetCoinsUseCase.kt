package com.assistant.cryptoapi.domain.use_case.get_coins

import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.data.repository.CoinRepository
import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    operator fun invoke(limit : Int): Flow<Resource<List<CoinListResponse>>> = flow {
        try {
            emit(Resource.Loading())
            val coinList = coinRepository.getCoins(limit).data // Получаем объект Coin
            emit(Resource.Success(coinList))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}