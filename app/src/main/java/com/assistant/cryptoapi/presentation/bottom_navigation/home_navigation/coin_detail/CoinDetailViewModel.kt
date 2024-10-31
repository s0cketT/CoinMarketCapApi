package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.cryptoapi.common.Constants
import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.domain.model.CoinDetails
import com.assistant.cryptoapi.domain.use_case.get_coin.GetCoinByIdUseCase
import com.assistant.cryptoapi.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state


    private val _star = mutableStateOf(false)
    val star: State<Boolean> = _star

    fun changeStar() { _star.value = !star.value }


    private val _coinId = mutableStateOf("")
    val coinId: State<String> = _coinId

    fun changeIndex(index: Int) { _coinId.value = index.toString() }


    fun openLink(url: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }


    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)

        }
    }

    private fun getCoin(coinId: String) {
        getCoinByIdUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "Unknown Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}

