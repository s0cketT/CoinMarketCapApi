package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.components

import android.util.Log
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
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.HomeViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.HourCoinsItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.TabRowItems
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.TopCoinsItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.CoinListItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.HourCoins
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.InfoCoins
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.components.InfoExchanges
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.TopCoins
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.ExchangeListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.components.ExchangeListItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.favorites_list.FavoritesViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.favorites_list.components.FavoritesListItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.global_metrics.components.GlobalMetrics
import com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation.ProfileViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation.register.RegisterViewModel
import com.assistant.cryptoapi.presentation.navigation.Screen
import com.assistant.cryptoapi.presentation.ui.theme.BackGround
import com.assistant.cryptoapi.presentation.ui.theme.IndicatorTabRow
import com.catching.pucks.database.DataBase.CoinDB
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navContriller: NavController,
    bottomNavController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    coinListViewModel: CoinListViewModel = hiltViewModel(),
    exchangeListViewModel: ExchangeListViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val coinList = coinListViewModel.state.value
    Log.d("vnenvue", coinList.toString())
    val exchangeList = exchangeListViewModel.state.value
    val favoritesList = favoritesViewModel.list.collectAsState(emptyList())

    val tabItems = TabRowItems()
    val tabTitles = listOf(tabItems.coins, tabItems.favorites, tabItems.exchanges)

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    val context = LocalContext.current

    var selectedTabIndex = homeViewModel.selectedTabIndex.value

    coinList.coins?.let { coins ->
        favoritesList.value?.forEach { coin ->
            val favoriteItem = coins.firstOrNull { it.id.toInt() == coin.coinId }

            if (favoriteItem != null) {
                favoritesViewModel.updateCoin(
                    CoinDB(
                        0,
                        favoriteItem.id.toInt(),
                        true,
                        favoriteItem.cmc_rank,
                        favoriteItem.quote.USD.price,
                        favoriteItem.quote.USD.market_cap,
                        favoriteItem.quote.USD.percent_change_24h,
                        favoriteItem.quote.USD.percent_change_1h,
                        favoriteItem.quote.USD.percent_change_7d,
                        favoriteItem.quote.USD.percent_change_30d,
                    )
                )
            } else {
                // Элемент не найден
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround),
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
                    .size((width * 0.9).dp, (height * 0.89).dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .size((width * 1).dp, (height * 0.16).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        GlobalMetrics(width, bottomNavController, navContriller, scope, drawerState)
                    }

                    Spacer(modifier = Modifier.size(10.dp))
                }

                stickyHeader {

                    Row(
                        modifier = Modifier
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
                                        Text(
                                            text = title,
                                            fontSize = 14.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
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

                            1 -> {

                                if (!registerViewModel.onCheckUser()) {
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
                    }

                    Box(
                        modifier = Modifier
                            .size((width * 0.9).dp, (width * 0.1).dp)
                            .background(BackGround)
                    ) {
                        when (selectedTabIndex) {
                            0 -> InfoCoins(width)
                            1 -> {
                                if (!registerViewModel.onCheckUser()) {
                                    InfoCoins(width)
                                }
                            }
                            2 -> InfoExchanges(width)
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

                        if (!registerViewModel.onCheckUser()) {

                            coinList.coins?.let { coins ->

                                val sortedCoins =
                                    if (coinListViewModel.activeIndex.value == 1) {
                                        if (coinListViewModel.arrow.value) favoritesList.value.sortedByDescending { it.cmc_rank.toInt() }
                                        else favoritesList.value.sortedBy { it.cmc_rank.toInt() }
                                    } else if (coinListViewModel.activeIndex.value == 2) {
                                        if (!coinListViewModel.arrow.value) favoritesList.value.sortedByDescending { it.market_cap }
                                        else favoritesList.value.sortedBy { it.market_cap }
                                    } else if (coinListViewModel.activeIndex.value == 3) {
                                        if (!coinListViewModel.arrow.value) favoritesList.value.sortedByDescending { it.price.toDouble() }
                                        else favoritesList.value.sortedBy { it.price.toDouble() }
                                    } else {
                                        if (!coinListViewModel.arrow.value) favoritesList.value.sortedByDescending { it.percent_change_24h }
                                        else favoritesList.value.sortedBy { it.percent_change_24h }
                                    }

                                //берем монету из БД
                                items(sortedCoins) { coin ->
                                    Log.d("coinren", coin.id.toString())
                                    val favoriteItem =
                                        coins.firstOrNull { it.id == coin.coinId.toString() }


                                    if (favoriteItem != null) {
                                        FavoritesListItem(
                                            favoriteItem,
                                            onItemClick = { navContriller.navigate(Screen.CoinDetailScreen.route + "/${coin.coinId}") }
                                        )
                                    }

                                    Spacer(modifier = Modifier.size((width * 0.05).dp))

                                }


                            }
                        } else {
                            item {
                                Column(
                                    modifier = Modifier
                                        .size((width * 1).dp, (height * 0.4).dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    SuggestionChip(
                                        onClick = { navContriller.navigate(Screen.LogInScreen.route) },
                                        label = {
                                            Text(
                                                "Войти",
                                                color = Color.Gray,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.W500
                                            )
                                        }
                                    )

                                    Spacer(modifier = Modifier.size((width * 0.1).dp))

                                    SuggestionChip(
                                        onClick = { navContriller.navigate(Screen.RegisterScreen.route) },
                                        label = {
                                            Text(
                                                "Регистрация",
                                                color = Color.Gray,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.W500
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }

                    2 -> {
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