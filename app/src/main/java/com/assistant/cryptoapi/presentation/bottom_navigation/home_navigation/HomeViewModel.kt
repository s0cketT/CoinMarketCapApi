package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.assistant.cryptoapi.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private val _selectedTabIndex = mutableStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex

    fun changeSelectedTabIndex(index: Int) {
        _selectedTabIndex.value = index
    }

}