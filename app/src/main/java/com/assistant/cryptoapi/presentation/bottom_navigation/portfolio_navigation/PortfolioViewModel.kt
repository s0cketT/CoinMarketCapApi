package com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.cryptoapi.domain.model.CoinListResponse
import com.assistant.cryptoapi.domain.model.Quote
import com.assistant.cryptoapi.domain.model.USDData
import com.catching.pucks.database.DataBase.CoinDB
import com.catching.pucks.database.DataBase.CoinPortfolioDB
import com.catching.pucks.database.DataBase.MainDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(private val database: MainDatabase): ViewModel() {

    val list = database.daoPortfolio.getAllUsers()

    fun insertCoin(coin: CoinPortfolioDB) {
        viewModelScope.launch {
            database.daoPortfolio.insert(coin)
        }
    }

    fun updateCoin(coin: CoinPortfolioDB) {
        viewModelScope.launch {
            database.daoPortfolio.update(coin)
        }
    }

    fun deleteCoin(coin: CoinPortfolioDB) {
        viewModelScope.launch {
            database.daoPortfolio.delete(coin)
        }
    }


    val focusRequester = FocusRequester()

    private val _money = mutableDoubleStateOf(0.0)
    val money: State<Double> = _money


    private val _textSearch = mutableStateOf("")
    val textSearch: State<String> = _textSearch

    fun onSearchTextChange(text: String) { _textSearch.value = text }


    private val _isFocus = mutableStateOf(false)
    val isFocus: State<Boolean> = _isFocus

    fun changeIsFocus(temp: Boolean) { _isFocus.value = temp }


    private val _isVisible = mutableStateOf(false)
    val isVisible: State<Boolean> = _isVisible

    fun changeIsVisible() { _isVisible.value = !_isVisible.value }


    val coinClickSymbol = mutableStateOf("")
    val coinClick = mutableStateOf(CoinListResponse(
        "",
        "",
        "",
        "",
        Quote(
            USDData(
            0.0,
            "",
            0.0,
            0.0,
            0.0,
            0.0,
        )
        )
    ))


    private val _textCount = mutableStateOf("")
    val textCount: State<String> = _textCount

    fun onTextCount(text: String) { _textCount.value = text }

    private val _textPrice = mutableStateOf("")
    val textPrice: State<String> = _textPrice

    fun onTextPrice(text: String) { _textPrice.value = text }


    fun isValidNumber(input: String): Boolean {
        // Проверяем, чтобы первый символ не был '-' или '.'
        if (input.isNotEmpty() && (input[0] == '-' || input[0] == '.')) {
            return false
        }

        // Проверяем, чтобы длина строки не превышала 6 символов
        if (input.length > 8) {
            return false
        }

        // Регулярное выражение для проверки, что строка является числом (целым или с плавающей точкой)
        return input.isEmpty() || input.matches(Regex("^[+-]?\\d*\\.?\\d*\$"))
    }


    fun saveToSharedPreferences(context: Context, key: String, value: Float) {
        val sharedPref = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putFloat(key, value)
            apply()
        }
    }

    // Функция для загрузки значения из SharedPreferences
    fun loadFromSharedPreferences(context: Context, key: String): Float {
        val sharedPref = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        return sharedPref.getFloat(key, 0.0f) // Возвращаем 0.0f если ключ не найден
    }
}