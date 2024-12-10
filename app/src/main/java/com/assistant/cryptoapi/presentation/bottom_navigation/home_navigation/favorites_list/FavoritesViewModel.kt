package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.favorites_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catching.pucks.database.DataBase.CoinDB
import com.catching.pucks.database.DataBase.MainDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val database: MainDatabase): ViewModel() {


    val list = database.daoFavorite.getAllCoinFavorites()

    private val _index = mutableStateOf(-1)
    val index: State<Int> = _index

    fun changeIndex(temp: Int) { _index.value = temp }

    fun insertCoin(coin: CoinDB) {
        viewModelScope.launch {
            database.daoFavorite.insert(coin)
        }
    }

    fun updateCoin(coin: CoinDB) {
        viewModelScope.launch {
            database.daoFavorite.update(coin)
        }
    }

    fun deleteCoin(coin: CoinDB) {
        viewModelScope.launch {
            database.daoFavorite.delete(coin)
        }
    }
}