package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state


    private val _arrow = mutableStateOf(false)
    val arrow: State<Boolean> = _arrow

    private val _activeIndex = mutableStateOf(2)
    val activeIndex: State<Int> = _activeIndex

    private val _isReversed = mutableStateOf(true)
    val isReversed: State<Boolean> = _isReversed

    fun isIndex(id: Int) {
        _isReversed.value = !isReversed.value
        _arrow.value = false
        _activeIndex.value = id
    }

    fun isArrow() {
        _arrow.value = !arrow.value
    }


    private val _limit = mutableStateOf(100)
    val limit: State<Int> = _limit

    fun changeLimitCoin(temp: Int) {
        _limit.value = temp
        getCoins(limit.value)
    }


    private val _hour = mutableStateOf("24h")
    val hour: State<String> = _hour

    private val _hourCount = mutableStateOf(24)
    val hourCount: State<Int> = _hourCount

    fun changeHourCoins(temp: String, count: Int) {
        _hour.value = temp
        _hourCount.value = count
    }


    init {
        getCoins(limit.value)
    }

     private fun getCoins(limit: Int) {
        getCoinsUseCase(limit).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data)
                }

                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun formatLargeCurrency(amount: Double): String {
        return when {
            amount >= 1_000_000_000_000 -> String.format("%.2fT", amount / 1_000_000_000_000)
            amount >= 1_000_000_000 -> String.format("%.2fB", amount / 1_000_000_000)
            amount >= 1_000_000 -> String.format("%.2fM", amount / 1_000_000)
            else -> String.format("%.2f", amount)
        }
    }

    fun formatFloat(value: Double): String {
        // Убираем знак и округляем до 2 знаков после запятой
        val absoluteValue = Math.abs(value)
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(absoluteValue).replace('.', ',')
    }

    fun formatNumber(value: Double): String {
        val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
        numberFormat.minimumFractionDigits = 2
        numberFormat.maximumFractionDigits = 2
        return numberFormat.format(value)
    }
}