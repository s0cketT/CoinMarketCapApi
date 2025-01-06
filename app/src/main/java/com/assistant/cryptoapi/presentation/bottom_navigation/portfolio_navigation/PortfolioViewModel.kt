package com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.cryptoapi.domain.model.CoinListResponse
import com.assistant.cryptoapi.domain.model.Quote
import com.assistant.cryptoapi.domain.model.USDData
import com.assistant.cryptoapi.domain.repository.UserRepository
import com.catching.pucks.database.DataBase.CoinDB
import com.catching.pucks.database.DataBase.CoinPortfolioDB
import com.catching.pucks.database.DataBase.MainDatabase
import com.catching.pucks.database.DataBase.UserDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(private val database: MainDatabase, private val userRepository: UserRepository): ViewModel() {

    val list = database.daoPortfolio.getAllUsers()

    fun onCheckUser(): Boolean { return userRepository.mail == null }

    fun getUserMail() : String? { return userRepository.mail }
    fun getUserPas() : String? { return userRepository.pas }

    fun checkCredentials(users: List<CoinPortfolioDB>): Boolean {
        return users.any { user ->
            user.mail == userRepository.mail && user.password == userRepository.pas
        }
    }

    val focusRequester = FocusRequester()


    fun getMoney(list: List<CoinPortfolioDB>): Double {
        return list
            .filter { it.mail == userRepository.mail && it.password == userRepository.pas }
            .sumOf { it.buyPrice }
    }

    fun formatLargeCurrency(amount: Double): String {
        return when {
            amount >= 1_000_000_000_000 -> String.format("%.2fT", amount / 1_000_000_000_000)
            amount >= 1_000_000_000 -> String.format("%.2fB", amount / 1_000_000_000)
            amount >= 1_000_000 -> String.format("%.2fM", amount / 1_000_000)
            amount >= 100_000 -> String.format("%.2fK", amount / 1_000)
            amount >= 10_000 -> String.format("%.2fK", amount / 1_000)
            else -> String.format("%.2f", amount)
        }
    }


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

    private val _textCountIsNull = mutableStateOf(false)
    val textCountIsNull: State<Boolean> = _textCountIsNull
    fun onTextCountIsNull(textCountIsNull: Boolean) { _textCountIsNull.value = textCountIsNull }


    private val _textPrice = mutableStateOf("")
    val textPrice: State<String> = _textPrice
    fun onTextPrice(text: String) { _textPrice.value = text }

    private val _textPriceIsNull = mutableStateOf(false)
    val textPriceIsNull: State<Boolean> = _textPriceIsNull
    fun onTextPriceIsNull(textPriceIsNull: Boolean) { _textPriceIsNull.value = textPriceIsNull }


    var repetition = mutableStateOf(CoinPortfolioDB(
        -1,
        "",
        "",
        0,
        "",
        "",
        "",
        "",
        0.0,
        0.0,
        )
    )


    fun calculateAveragePurchasePrice(
        firstPrice: Double,
        firstQuantity: Double,
        secondPrice: Double,
        secondQuantity: Double
    ): Double {
        // Суммарная стоимость (цена * количество) для обеих покупок
        val totalCost = (firstPrice * firstQuantity) + (secondPrice * secondQuantity)

        // Общее количество монет
        val totalQuantity = firstQuantity + secondQuantity

        // Средняя цена (если количество монет > 0)
        return if (totalQuantity > 0) totalCost / totalQuantity else 0.0
    }

    fun findItemWithId(list: List<CoinPortfolioDB>, targetId: Int): CoinPortfolioDB? {
        return list.find { it.coinId == targetId } ?: CoinPortfolioDB(
            -1,
            "",
            "",
            0,
            "",
            "",
            "",
            "",
            0.0,
            0.0,
        )
    }

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



    fun formatNumber(value: Double): String {
        val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
        numberFormat.minimumFractionDigits = 2
        numberFormat.maximumFractionDigits = 2
        return numberFormat.format(value)
    }

    fun formatNumberToTrueDouble(input: String): Double {
        return input.replace(",", ".").toDouble()
    }

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


    fun isMailExists(list: List<CoinPortfolioDB>, mail: String): Boolean {
        return list.any { it.mail == mail }
    }

}
