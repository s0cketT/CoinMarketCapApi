package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.domain.model.ExchangeListResponse
import com.assistant.cryptoapi.domain.use_case.get_exchange.GetExchangeUseCase
import com.assistant.cryptoapi.domain.use_case.get_global_matrics.GetGlobalMetricsUseCase
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExchangeListViewModel @Inject constructor(
    private val getExchangeUseCase: GetExchangeUseCase
): ViewModel() {
    private val _state = mutableStateOf(ExchangeListState())
    val state: State<ExchangeListState> = _state

    private val _number = mutableStateOf(0)
    val number: State<Int> = _number

    fun incrementNumber() { _number.value++ }
    init {
        getExchanges(100, "volume_24h")
    }

    private fun getExchanges(limit: Int, sort: String) {
        getExchangeUseCase(limit, sort).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ExchangeListState(exchanges = result.data)
                }

                is Resource.Loading -> {
                    _state.value = ExchangeListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = ExchangeListState(error = result.message)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}