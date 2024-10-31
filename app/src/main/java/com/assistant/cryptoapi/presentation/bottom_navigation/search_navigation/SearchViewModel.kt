package com.assistant.cryptoapi.presentation.bottom_navigation.search_navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.ExchangeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {

    val focusRequester = FocusRequester()

    private val _text = mutableStateOf("")
    val text: State<String> = _text

    fun onSearchTextChange(text: String) { _text.value = text }


    private val _isFocus = mutableStateOf(false)
    val isFocus: State<Boolean> = _isFocus

    fun changeIsFocus(temp: Boolean) { _isFocus.value = temp }


    private val _indexInfo = mutableStateOf(1)
    val indexInfo: State<Int> = _indexInfo

    fun changeIndexInfo(temp: Int) { _indexInfo.value = temp }
}