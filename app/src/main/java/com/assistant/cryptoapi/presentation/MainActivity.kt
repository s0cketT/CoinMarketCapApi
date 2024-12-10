package com.assistant.cryptoapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.assistant.cryptoapi.presentation.navigation.components.Navigation
import com.assistant.cryptoapi.presentation.ui.theme.CryptoApiTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

                Navigation()
                    //ModalDrawerRightSideExample()
                //RightSideModalDrawerDemo()
            }
        }
    }
}




@Composable
fun ModalDrawerRightSideExample() {
    // Управление состоянием выдвижной панели
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen, // Включение жестов только при открытом состоянии
        scrimColor = MaterialTheme.colorScheme.scrim, // Затемнение фона
        drawerContent = {
            // Содержимое выдвижной панели
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f) // Панель занимает половину экрана
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Меню",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Divider(color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Пункт 1",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Обработка нажатия */ }
                            .padding(8.dp)
                    )
                    Text(
                        text = "Пункт 2",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Обработка нажатия */ }
                            .padding(8.dp)
                    )
                    Text(
                        text = "Пункт 3",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Обработка нажатия */ }
                            .padding(8.dp)
                    )
                }
            }
        },
        content = {
            // Основной экран
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        // Открываем выдвижную панель
                        scope.launch { drawerState.open() }
                    }
                ) {
                    Text("Открыть панель справа")
                }
            }
        }
    )
}



