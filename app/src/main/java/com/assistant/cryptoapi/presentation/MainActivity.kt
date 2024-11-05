package com.assistant.cryptoapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assistant.cryptoapi.presentation.bottom_navigation.components.BottomNavigationScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.CoinDetailScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.exchange_detail.components.ExchangeDetailScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.global_metrics.GlobalMetricsViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.components.AddNewTransaction
import com.assistant.cryptoapi.presentation.ui.theme.CryptoApiTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

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

                    composable(Screen.BottomNavigationPortfolioAddNewTransactionScreen.route) {
                        AddNewTransaction(
                            navController = navController,
                            width = width,
                            height = height
                            )
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

