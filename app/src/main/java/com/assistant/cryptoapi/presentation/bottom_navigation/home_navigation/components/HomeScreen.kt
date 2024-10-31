package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.assistant.cryptoapi.presentation.Screen
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.HomeViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.HourCoinsItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.TabRowItems
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_detail.CoinDetailViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.TopCoinsItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.CoinListItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.HourCoins
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.InfoCoins
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.components.InfoExchanges
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.TopCoins
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.ExchangeListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.components.ExchangeListItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.global_metrics.components.GlobalMetrics
import com.assistant.cryptoapi.presentation.ui.theme.BackGround
import com.assistant.cryptoapi.presentation.ui.theme.IndicatorTabRow


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navContriller: NavController,
    bottomNavController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    coinListViewModel: CoinListViewModel = hiltViewModel(),
    exchangeListViewModel: ExchangeListViewModel = hiltViewModel()
) {
    val coinList = coinListViewModel.state.value
    val exchangeList = exchangeListViewModel.state.value

    val tabItems = TabRowItems()
    val tabTitles = listOf(tabItems.coins, tabItems.exchanges)

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    val context = LocalContext.current

    var selectedTabIndex = homeViewModel.selectedTabIndex.value

    Box(
        modifier = Modifier.fillMaxSize().background(BackGround),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(top = (height * 0.03).dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(
                modifier = Modifier
                    .size((width * 0.9).dp, (height * 0.9).dp)
            ) {
                    item {
                        Box(
                            modifier = Modifier
                                .size((width * 1).dp, (height * 0.16).dp),
                            contentAlignment = Alignment.Center
                        ) {
                            GlobalMetrics(width, bottomNavController)
                        }

                        Spacer(modifier = Modifier.size(10.dp))
                    }

                    stickyHeader {

                        Row(modifier = Modifier
                            .size((width * 0.9).dp, (width * 0.15).dp)
                            .background(BackGround),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            TabRow(
                                selectedTabIndex = selectedTabIndex,
                                indicator = { tabPositions ->
                                    TabRowDefaults.Indicator(
                                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                        color = IndicatorTabRow
                                    )
                                },
                                containerColor = Color.Transparent
                            ) {
                                tabTitles.forEachIndexed { index, title ->
                                    Tab(
                                        selected = selectedTabIndex == index,
                                        onClick = {
                                            selectedTabIndex = index
                                            homeViewModel.changeSelectedTabIndex(selectedTabIndex)
                                                  },
                                        text = {
                                            Text(text = title,
                                                fontSize = 16.sp,
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold
                                        ) }
                                    )
                                }
                            }
                        }

                        Row(modifier = Modifier
                            .size((width * 0.9).dp, (width * 0.08).dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { }
                            .background(BackGround),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            when (selectedTabIndex) {
                                0 -> {
                                    TopCoins(
                                        width,
                                        listOf(
                                            TopCoinsItem("Топ 100", 100),
                                            TopCoinsItem("Топ 200", 200),
                                            TopCoinsItem("Топ 500", 500)
                                        ),
                                        onItemClick = {
                                            Toast.makeText(
                                                context,
                                                it.text,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        })

                                    Spacer(modifier = Modifier.size((width * 0.05).dp))

                                    HourCoins(
                                        width,
                                        listOf(
                                            HourCoinsItem("1 час", "1h%", 1),
                                            HourCoinsItem("24 часа", "24h%", 24),
                                            HourCoinsItem("7 дней", "7d%", 7),
                                            HourCoinsItem("30 дней", "30d%", 30),
                                        ),
                                        onItemClick = {
                                            Toast.makeText(
                                                context,
                                                it.text,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        })
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size((width * 0.9).dp, (width * 0.1).dp)
                                .background(BackGround)
                        ) {
                            when (selectedTabIndex) {
                                0 -> InfoCoins(width)
                                1 -> InfoExchanges(width)
                            }
                        }
                    }

                    when (selectedTabIndex) {
                        0 -> {
                            coinList.coins?.let { coins ->
                                val sortedCoins = if (coinListViewModel.activeIndex.value == 1) {
                                    if (coinListViewModel.arrow.value) coins.sortedByDescending { it.cmc_rank.toInt() }
                                    else coins.sortedBy { it.cmc_rank.toInt() }
                                } else if (coinListViewModel.activeIndex.value == 2) {
                                    if (!coinListViewModel.arrow.value) coins.sortedByDescending { it.quote.USD.market_cap }
                                    else coins.sortedBy { it.quote.USD.market_cap }
                                } else if (coinListViewModel.activeIndex.value == 3) {
                                    if (!coinListViewModel.arrow.value) coins.sortedByDescending { it.quote.USD.price.toDouble() }
                                    else coins.sortedBy { it.quote.USD.price.toDouble() }
                                } else {
                                    if (!coinListViewModel.arrow.value) coins.sortedByDescending { it.quote.USD.percent_change_24h }
                                    else coins.sortedBy { it.quote.USD.percent_change_24h }
                                }
                                items(sortedCoins) { coin ->
                                    CoinListItem(
                                        coin = coin,
                                        onItemClick = { navContriller.navigate(Screen.CoinDetailScreen.route + "/${coin.id}") }
                                    )
                                    Spacer(modifier = Modifier.size((width * 0.05).dp))
                                }
                            }
                        }
                        1 -> {
                            exchangeList.exchanges?.let { exchanges ->

                                items(exchanges) { exchange ->

                                    ExchangeListItem(
                                        width = width,
                                        height = height,
                                        exchange = exchange,
                                        onItemClick = { navContriller.navigate(Screen.ExchangeDetailScreen.route + "/${exchange.id}") }
                                        )
                                    Spacer(modifier = Modifier.size((width * 0.05).dp))
                                }

                            }
                        }
                    }

            }
        }

        when (selectedTabIndex) {
            0 -> {
                if (coinList.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                if (coinList.error.isNotBlank()) {
                    Text(
                        text = coinList.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
            }


            1 -> {
                if (exchangeList.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                if (exchangeList.error.isNotBlank()) {
                    Text(
                        text = exchangeList.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}