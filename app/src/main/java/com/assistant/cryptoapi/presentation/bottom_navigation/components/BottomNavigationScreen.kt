package com.assistant.cryptoapi.presentation.bottom_navigation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assistant.cryptoapi.presentation.Screen
import com.assistant.cryptoapi.presentation.bottom_navigation.BottomNavigationViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.components.HomeScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.components.PortfolioScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.search_navigation.components.SearchScreen
import com.assistant.cryptoapi.presentation.ui.theme.BackGroundBottomNav

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationScreen(
    navController: NavController,
    bottomNavigationViewModel: BottomNavigationViewModel = hiltViewModel()) {

    val bottomNavController = rememberNavController()
    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar(modifier = Modifier
                    .size((width * 1).dp, (height * 0.15).dp),
                        containerColor = BackGroundBottomNav
                ) {
                    bottomNavigationViewModel.items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = bottomNavigationViewModel.selectedItemIndex.value == index,
                            onClick = {
                                bottomNavigationViewModel.selectItem(index)
                                bottomNavController.navigate(item.title)
                            },
                            label = {
                                Text(text = item.title,
                                    color = Color.Gray
                                    )
                            },
                            alwaysShowLabel = false,
                            icon = {

                                    Icon(
                                        imageVector = if (index == bottomNavigationViewModel.selectedItemIndex.value) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title,
                                        tint = if (index == bottomNavigationViewModel.selectedItemIndex.value) Color.Blue
                                                else Color.Gray
                                    )

                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        ) {
            NavHost(navController = bottomNavController, startDestination = Screen.BottomNavigationHomeScreen.route) {
                composable(Screen.BottomNavigationHomeScreen.route) {
                    HomeScreen(
                        navContriller = navController,
                        bottomNavController = bottomNavController,
                    )
                }

                composable(Screen.BottomNavigationSearchScreen.route) {
                    SearchScreen(
                        navController = navController,
                        width = width, height = height)
                }

                composable(Screen.BottomNavigationPortfolioScreen.route) {
                    PortfolioScreen(navController = navController)
                }
            }
        }
    }
}
