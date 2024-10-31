package com.assistant.cryptoapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assistant.cryptoapi.presentation.bottom_navigation.components.BottomNavigationScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_detail.components.CoinDetailScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.exchange_detail.components.ExchangeDetailScreen
import com.assistant.cryptoapi.presentation.ui.theme.CryptoApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoApiTheme {
                val width = LocalConfiguration.current.screenWidthDp
                val height = LocalConfiguration.current.screenHeightDp


                /*val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.CoinListScreen.route) {
                    composable(Screen.CoinListScreen.route) {
                        CoinListScreen(navController)
                    }

                    composable(Screen.CoinDetailScreen.route + "/{coinId}") {
                        CoinDetailScreen()
                    }
                }*/
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.BottomNavigationScreen.route) {
                    composable("${Screen.BottomNavigationScreen.route}") {
                        BottomNavigationScreen(navController = navController)
                    }

                    composable("${Screen.CoinDetailScreen.route}/{coinId}") {
                        CoinDetailScreen(width, height, navController)
                    }

                    composable("${Screen.ExchangeDetailScreen.route}/{coinId}") {
                        ExchangeDetailScreen(width, height, navController)
                    }
                }
                //CoinDetailScreen(width)
            }
        }
    }
}



data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

