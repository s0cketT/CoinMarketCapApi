package com.assistant.cryptoapi.presentation.bottom_navigation.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assistant.cryptoapi.presentation.bottom_navigation.BottomNavigationViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.components.HomeScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.PortfolioViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.components.PortfolioScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.search_navigation.components.SearchScreen
import com.assistant.cryptoapi.presentation.navigation.Screen
import com.assistant.cryptoapi.presentation.ui.theme.BackGroundBottomNav
import com.assistant.cryptoapi.presentation.ui.theme.TopCoinsColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationScreen(
    navController: NavController,
    portfolioViewModel2: PortfolioViewModel,
    bottomNavigationViewModel: BottomNavigationViewModel = hiltViewModel()) {

    val bottomNavController = rememberNavController()
    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    val context = LocalContext.current

    // Управление состоянием выдвижной панели
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen, // Включение жестов только при открытом состоянии
        scrimColor = Color.Black.copy(alpha = 0.4f), // Затемнение фона
        drawerContent = {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f) // Панель занимает половину экрана
                    .background(TopCoinsColor)
                    .padding(16.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = (width * 0.1).dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Favorite Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size((width * 0.12).dp)
                        )

                        if (bottomNavigationViewModel.onCheckUser()) {
                            SuggestionChip(
                                onClick = {
                                    navController.navigate(Screen.LogInScreen.route)
                                    scope.launch { drawerState.close()
                                    }
                                },
                                label = { Text("Войти",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W500
                                )
                                }
                            )

                            SuggestionChip(
                                onClick = {
                                    navController.navigate(Screen.RegisterScreen.route)
                                    scope.launch { drawerState.close() }
                                },
                                label = { Text("Регистрация",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W500
                                )
                                }
                            )

                        }
                        else {
                            IconButton(
                                {
                                    bottomNavigationViewModel.onExitUser()

                                    Toast.makeText(context, "Вы успешно вышли", Toast.LENGTH_SHORT).show()

                                    navController.navigate(Screen.BottomNavigationScreen.route)
                                },
                                modifier = Modifier.size((width * 0.1).dp)
                                ) {
                                Icon(
                                    imageVector = Icons.Filled.ExitToApp,
                                    contentDescription = "Favorite Icon",
                                    tint = Color.Gray,
                                    modifier = Modifier.size((width * 0.12).dp)
                                )
                            }
                        }

                    }
                }
            }
        },
        content = {

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
                                scope = scope,
                                drawerState = drawerState
                            )
                        }

                        composable(Screen.BottomNavigationSearchScreen.route) {
                            SearchScreen(
                                navController = navController,
                                width = width, height = height)
                        }

                        composable(Screen.BottomNavigationPortfolioScreen.route) {
                            PortfolioScreen(navController = navController, portfolioViewModel2)
                        }
                    }
                }
            }

        }
    )



}
