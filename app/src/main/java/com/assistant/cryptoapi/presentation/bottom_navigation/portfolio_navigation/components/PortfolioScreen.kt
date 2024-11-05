package com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.assistant.cryptoapi.presentation.Screen
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.PortfolioViewModel
import com.assistant.cryptoapi.presentation.ui.theme.BackGround
import com.assistant.cryptoapi.presentation.ui.theme.BackGroundBottomNav
import org.w3c.dom.Text

@Composable
fun PortfolioScreen(
    navController: NavController,
    portfolioViewModel: PortfolioViewModel = hiltViewModel()
) {

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    val list = portfolioViewModel.list.collectAsState(emptyList())

    Box(modifier = Modifier.fillMaxSize().background(BackGround),
        contentAlignment = Alignment.Center
        ) {

        Column(modifier = Modifier.fillMaxWidth(0.9f).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(modifier = Modifier.size((height * 0.1).dp))

            Row(modifier = Modifier.size((width * 0.9).dp, (height * 0.1).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Text(text = "${portfolioViewModel.money.value}$")

                Box(modifier = Modifier
                    .size((width * 0.13).dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(BackGroundBottomNav)
                    .clickable { navController.navigate(Screen.BottomNavigationPortfolioAddNewTransactionScreen.route) },
                    contentAlignment = Alignment.Center
                    ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Favorite Icon",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size((width * 0.08).dp)
                    )
                }
            }

            PortfolioInfoCoin(width)

            LazyColumn(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                items(list.value) { coin ->
                    PortfolioItem(width, coin)
                }
            }
        }

    }

}