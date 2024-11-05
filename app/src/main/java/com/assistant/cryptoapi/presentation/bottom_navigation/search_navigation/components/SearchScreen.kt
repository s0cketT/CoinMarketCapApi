package com.assistant.cryptoapi.presentation.bottom_navigation.search_navigation.components

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.assistant.cryptoapi.presentation.Screen
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.CoinDetailScreen
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.CoinListItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.ExchangeListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.components.ExchangeListItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.components.InfoExchanges
import com.assistant.cryptoapi.presentation.bottom_navigation.search_navigation.SearchViewModel
import com.assistant.cryptoapi.presentation.ui.theme.BackGround
import com.assistant.cryptoapi.presentation.ui.theme.BackGroundBottomNav
import com.assistant.cryptoapi.presentation.ui.theme.TopCoinsColor

@Composable
fun SearchScreen(
    navController: NavController,
    width: Int,
    height: Int,
    searchViewModel: SearchViewModel = hiltViewModel(),
    coinsViewModel: CoinListViewModel = hiltViewModel(),
    exchangesViewModel: ExchangeListViewModel = hiltViewModel(),
    ) {

    val focusRequester = searchViewModel.focusRequester

    val coinsList = coinsViewModel.state.value
    val exchangesList = exchangesViewModel.state.value

    val text = searchViewModel.text.value


    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackGround)
        .padding(top = (height * 0.05).dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Box(modifier = Modifier
            .size((width * 0.9).dp, (width * 0.135).dp),
            contentAlignment = Alignment.Center
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { searchViewModel.onSearchTextChange(it) },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(50.dp))
                    .onFocusChanged {
                        searchViewModel.changeIsFocus(it.isFocused)
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
                        onClick = { searchViewModel.onSearchTextChange("") },
                        modifier = Modifier.size((width * 0.07).dp)
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray,)
                    } },
                trailingIcon = {
                    if (text != "") {
                        IconButton(
                            onClick = { searchViewModel.onSearchTextChange("") },
                            modifier = Modifier
                                .size((width * 0.06).dp)
                        ) {
                            Icon(Icons.Outlined.Close, contentDescription = null, tint = Color.DarkGray,
                                modifier = Modifier.size((width * 0.04).dp)
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color.Gray)
                                )
                        }
                    }
                },
                placeholder = {
                    if (text.isEmpty()) {
                        Text(text = "Поиск монет")
                    }
                },

                shape = RoundedCornerShape(50)
            )
        }

        Spacer(modifier = Modifier.size((width * 0.05).dp))

        Row(modifier = Modifier.size((width * 0.9).dp, (width * 0.08).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Button(onClick = { searchViewModel.changeIndexInfo(1) },
                modifier = Modifier.size((width * 0.2).dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(TopCoinsColor),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = "Монеты",
                    color = if (searchViewModel.indexInfo.value == 1) Color.White else Color.Gray,
                    fontSize = 14.sp
                    )
            }

            Spacer(modifier = Modifier.size((width * 0.05).dp))

            Button(onClick = { searchViewModel.changeIndexInfo(2) },
                modifier = Modifier.size((width * 0.2).dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(TopCoinsColor),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = "Биржи",
                    color = if (searchViewModel.indexInfo.value == 2) Color.White else Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.size((width * 0.05).dp))

        Card(modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(),
            colors = CardDefaults.cardColors(
                containerColor = BackGroundBottomNav
            )
        ) {

            if (searchViewModel.indexInfo.value == 1) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Row(modifier = Modifier.size((width * 1).dp, (width * 0.1).dp)) {
                        SearchCoinInfo(width)
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

                                CoinSearchListItem(
                                    coin = coin,
                                    width = width,
                                    onItemClick = { navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}") }
                                )

                                Spacer(modifier = Modifier.size((width * 0.05).dp))
                            }
                        }
                    }
                }
            }
            else if (searchViewModel.indexInfo.value == 2) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Row(modifier = Modifier.size((width * 1).dp, (width * 0.1).dp)) {
                        InfoExchanges(width)
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        exchangesList.exchanges?.let { exchanges ->
                            items(exchanges.filter {
                                it.name.contains(
                                    text,
                                    ignoreCase = true
                                )
                            }) { exchange ->

                                ExchangeListItem(
                                    width = width,
                                    height = height,
                                    exchange = exchange,
                                    onItemClick = { navController.navigate(Screen.ExchangeDetailScreen.route + "/${exchange.id}") }
                                )
                                Spacer(modifier = Modifier.size((width * 0.05).dp))
                            }
                        }
                    }
                }
            }
        }
    }

}