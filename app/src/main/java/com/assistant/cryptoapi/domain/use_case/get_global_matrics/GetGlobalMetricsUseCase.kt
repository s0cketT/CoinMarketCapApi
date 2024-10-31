package com.assistant.cryptoapi.domain.use_case.get_global_matrics

import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.data.repository.CoinRepository
import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinDetail
import com.assistant.cryptoapi.domain.model.GlobalMetrics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGlobalMetricsUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    operator fun invoke(): Flow<Resource<GlobalMetrics>> = flow {
        try {
            emit(Resource.Loading())
            val globalMetrics = coinRepository.getGlobalMetrics()
            emit(Resource.Success(globalMetrics))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}