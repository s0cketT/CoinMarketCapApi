package com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.assistant.cryptoapi.presentation.Screen
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.ExchangeListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.PortfolioViewModel
import com.assistant.cryptoapi.presentation.ui.theme.BackGround
import com.assistant.cryptoapi.presentation.ui.theme.BackGroundBottomNav

@Composable
fun AddNewTransaction(
    navController: NavController,
    width: Int,
    height: Int,
    portfolioViewModel: PortfolioViewModel = hiltViewModel(),
    coinsViewModel: CoinListViewModel = hiltViewModel(),
    exchangesViewModel: ExchangeListViewModel = hiltViewModel(),
) {

    val focusRequester = portfolioViewModel.focusRequester

    val coinsList = coinsViewModel.state.value
    val exchangesList = exchangesViewModel.state.value

    val text = portfolioViewModel.textSearch.value

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .padding(top = (height * 0.05).dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (portfolioViewModel.isVisible.value) {
            PortfolioDialog(width)
        }

        Box(
            modifier = Modifier.size((width * 0.9).dp, (height * 0.1).dp),
            contentAlignment = Alignment.CenterStart
        ) {

            IconButton(
                onClick = { navController.navigate(Screen.BottomNavigationScreen.route) },
                modifier = Modifier.size((width * 0.08).dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Favorite Icon",
                    tint = Color.Gray,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {


                Text(
                    text = "Добавить транзакцию",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

            }
        }

        Box(
            modifier = Modifier
                .size((width * 0.9).dp, (width * 0.135).dp),
            contentAlignment = Alignment.Center
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { portfolioViewModel.onSearchTextChange(it) },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(50.dp))
                    .onFocusChanged {
                        portfolioViewModel.changeIsFocus(it.isFocused)
                    }
                    .focusRequester(focusRequester),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Blue,
                    focusedTextColor = Color.White,
                    unfocusedContainerColor = BackGroundBottomNav,
                    focusedContainerColor = BackGroundBottomNav,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray
                ),

                leadingIcon = {
                    IconButton(
                        onClick = { portfolioViewModel.onSearchTextChange("") },
                        modifier = Modifier.size((width * 0.07).dp)
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                    }
                },
                trailingIcon = {
                    if (text != "") {
                        IconButton(
                            onClick = { portfolioViewModel.onSearchTextChange("") },
                            modifier = Modifier
                                .size((width * 0.06).dp)
                        ) {
                            Icon(
                                Icons.Outlined.Close,
                                contentDescription = null,
                                tint = Color.DarkGray,
                                modifier = Modifier
                                    .size((width * 0.04).dp)
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color.Gray)
                            )
                        }
                    }
                },
                placeholder = {
                    if (text.isEmpty()) {
                        Text(text = "Найти монеты..")
                    }
                },

                shape = RoundedCornerShape(50)
            )
        }

        Spacer(modifier = Modifier.size((width * 0.05).dp))


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.size((width * 0.9).dp, (width * 0.1).dp),
                contentAlignment = Alignment.CenterStart
                ) {
                Text(
                    text = "Добав. из сп. набл.",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                coinsList.coins?.let { coins ->
                    items(coins.filter {
                        it.symbol.contains(
                            text,
                            ignoreCase = true
                        )
                    }) { coin ->
                        Spacer(modifier = Modifier.size(5.dp))
                        Divider()
                        Spacer(modifier = Modifier.size(5.dp))

                        PortfolioSearchCoinListItem(
                            coin = coin,
                            width = width
                        )

                    }
                }
            }
        }

    }
}