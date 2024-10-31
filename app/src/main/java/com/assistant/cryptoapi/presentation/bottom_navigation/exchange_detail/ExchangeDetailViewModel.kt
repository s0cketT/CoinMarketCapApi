package com.assistant.cryptoapi.presentation.bottom_navigation.exchange_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.cryptoapi.common.Constants
import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.domain.use_case.get_coin.GetCoinByIdUseCase
import com.assistant.cryptoapi.domain.use_case.get_exchange.GetExchangeUseCase
import com.assistant.cryptoapi.domain.use_case.get_exchange_by_id.GetExchangeByIdUseCase
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_detail.CoinDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExchangeDetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetExchangeByIdUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(ExchangeDetailState())
    val state: State<ExchangeDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getExchange(coinId)

        }
    }

    private fun getExchange(coinId: String) {
        getCoinByIdUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ExchangeDetailState(exchange = result.data)
                }
                is Resource.Loading -> {
                    _state.value = ExchangeDetailState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = ExchangeDetailState(error = result.message ?: "Unknown Error")
                }
            }
        }.launchIn(viewModelScope)
    }

}