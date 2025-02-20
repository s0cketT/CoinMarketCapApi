package com.assistant.cryptoapi.presentation.bottom_navigation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.assistant.cryptoapi.domain.repository.UserRepository
import com.assistant.cryptoapi.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class BottomNavigationViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    val items = listOf(
        BottomNavigationItem(
            title = Screen.BottomNavigationHomeScreen.route,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = Screen.BottomNavigationSearchScreen.route,
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = Screen.BottomNavigationPortfolioScreen.route,
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List,
            hasNews = true,
        ),
    )

    fun onCheckUser(): Boolean { return userRepository.mail == null }

    fun onExitUser() {
        userRepository.mail = null
        userRepository.pas = null
    }

    private val _selectedItemIndex = mutableIntStateOf(0)
    val selectedItemIndex: State<Int> = _selectedItemIndex


    fun selectItem(index: Int) {
        _selectedItemIndex.value = index

    }


}