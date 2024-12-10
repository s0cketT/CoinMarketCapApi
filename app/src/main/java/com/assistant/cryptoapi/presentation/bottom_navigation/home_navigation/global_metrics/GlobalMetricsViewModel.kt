package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.global_metrics

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.cryptoapi.common.Constants
import com.assistant.cryptoapi.common.Resource
import com.assistant.cryptoapi.domain.model.GlobalMetrics
import com.assistant.cryptoapi.domain.use_case.get_coins.GetCoinsUseCase
import com.assistant.cryptoapi.domain.use_case.get_global_matrics.GetGlobalMetricsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class GlobalMetricsViewModel @Inject constructor(
    private val getGlobalMetricsUseCase: GetGlobalMetricsUseCase,
    savedStateHandle: SavedStateHandle
    ) : ViewModel() {

    private val _globalMetricsState = mutableStateOf(GlobalMetricsState())
    val globalMetricsState: State<GlobalMetricsState> = _globalMetricsState


    init {
        getGlobalMetrics()
    }

    private fun getGlobalMetrics() {
        getGlobalMetricsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _globalMetricsState.value = GlobalMetricsState(coin = result.data)
                }
                is Resource.Loading -> {
                    _globalMetricsState.value = GlobalMetricsState(isLoading = true)
                }
                is Resource.Error -> {
                    _globalMetricsState.value = GlobalMetricsState(error = result.message)
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
}