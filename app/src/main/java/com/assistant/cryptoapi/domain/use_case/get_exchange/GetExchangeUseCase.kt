package com.assistant.cryptoapi.domain.use_case.get_exchange

import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.data.repository.CoinRepository
import com.assistant.cryptoapi.domain.model.CoinListResponse
import com.assistant.cryptoapi.domain.model.ExchangeListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetExchangeUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    operator fun invoke(limit : Int, sort: String): Flow<Resource<List<ExchangeListResponse>>> = flow {
        try {
            emit(Resource.Loading())
            val exchangeList = coinRepository.getExchanges(limit, sort).data
            emit(Resource.Success(exchangeList))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}