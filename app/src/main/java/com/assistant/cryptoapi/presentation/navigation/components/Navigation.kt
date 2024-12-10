package com.assistant.cryptoapi.presentation.navigation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.CoinDetailScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.components.BottomNavigationScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.exchange_detail.components.ExchangeDetailScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.PortfolioViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.components.AddNewTransaction
import com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation.ProfileViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation.log_in.components.LogInScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation.register.components.RegisterScreen
import com.assistant.cryptoapi.presentation.navigation.Screen

@Composable
fun Navigation() {

    val portfolioViewModel: PortfolioViewModel = hiltViewModel()

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    val navController = rememberNavController()

    val profileViewModel: ProfileViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.BottomNavigationScreen.route
    ) {

        composable("${Screen.BottomNavigationScreen.route}") {
            BottomNavigationScreen(navController = navController, portfolioViewModel)
        }

        composable("${Screen.CoinDetailScreen.route}/{coinId}") {
            CoinDetailScreen(width, height, navController)
        }

        composable("${Screen.ExchangeDetailScreen.route}/{coinId}") {
            ExchangeDetailScreen(width, height, navController)
        }

        composable(Screen.BottomNavigationPortfolioAddNewTransactionScreen.route) {
            AddNewTransaction(
                navController = navController,
                width = width,
                height = height,
                portfolioViewModel
            )
        }

        composable(Screen.LogInScreen.route) {
            LogInScreen(width, height, navController)
        }

        composable(Screen.RegisterScreen.route) {
            RegisterScreen(width, height, navController)
        }

    }
}