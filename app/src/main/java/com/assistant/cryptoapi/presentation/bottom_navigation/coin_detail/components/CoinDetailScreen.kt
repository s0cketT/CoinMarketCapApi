package com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.assistant.cryptoapi.presentation.Screen
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.CoinDetailViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.officia_links.GitHub
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.officia_links.WebSite
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.officia_links.WhitePaper
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.social_networks.Chat
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.social_networks.Facebook
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.social_networks.Reddit
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.social_networks.Twitter
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.favorites_list.FavoritesViewModel
import com.assistant.cryptoapi.presentation.ui.theme.BackGround
import com.assistant.cryptoapi.presentation.ui.theme.StarCoinDetail
import com.catching.pucks.database.DataBase.CoinDB

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    width: Int,
    height: Int,
    navController: NavController,
    coinDetailviewModel: CoinDetailViewModel = hiltViewModel(),
    coinListViewModel: CoinListViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val coinDetail = coinDetailviewModel.state.value
    val coinList = coinListViewModel.state.value
    val list = favoritesViewModel.list.collectAsState(emptyList())

    val favoriteItem = list.value.firstOrNull { it.coinId == coinDetailviewModel.coinId.value }

    coinList.coins.let { coins ->

    }

    if (favoriteItem != null) {
        coinDetailviewModel.changeStar(favoriteItem.is_favorites)
        favoritesViewModel.changeIndex(favoriteItem.id)
    } else {
        // Элемент не найден
    }

    Log.d("listF", list.value.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .padding(10.dp)
                .padding(top = (height * 0.03).dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            coinList.coins.let { coins ->
                val coinListItem = coins.firstOrNull { it.id == coinDetailviewModel.coinId.value.toString() }

                if (coinListItem != null) {
                    Log.d("priceU", coinListItem.quote.USD.price)
                } else {
                    // Элемент не найден
                }
                coinDetail.coin?.let { coinDetail ->

                    Row(
                        modifier = Modifier.size((width * 1).dp, (width * 0.1).dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.size((width * 0.55).dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
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

                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(coinDetail.logo),
                                    contentDescription = "",
                                    modifier = Modifier.size((width * 0.08).dp)
                                )

                                Spacer(modifier = Modifier.size(5.dp))

                                Text(
                                    text = coinDetail.symbol,
                                    fontSize = 20.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.size((width * 0.3).dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = { },
                                modifier = Modifier.size((width * 0.07).dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "",
                                    tint = Color.Gray,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            IconButton(
                                onClick = {
                                    coinDetailviewModel.changeStar(!coinDetailviewModel.star.value)
                                    if (coinDetailviewModel.star.value) {
                                        favoritesViewModel.insertCoin(
                                            CoinDB(
                                                0,
                                                coinDetailviewModel.coinId.value,
                                                coinDetailviewModel.star.value,
                                                coinListItem!!.cmc_rank,
                                                coinListItem?.quote?.USD?.price!!,
                                                coinListItem.quote.USD.market_cap,
                                                coinListItem.quote.USD.percent_change_24h,
                                                coinListItem.quote.USD.percent_change_1h,
                                                coinListItem.quote.USD.percent_change_7d,
                                                coinListItem.quote.USD.percent_change_30d,
                                            )
                                        )
                                    } else {
                                        favoritesViewModel.deleteCoin(
                                            CoinDB(
                                                favoritesViewModel.index.value,
                                                coinDetailviewModel.coinId.value,
                                                coinDetailviewModel.star.value,
                                                coinListItem!!.cmc_rank,
                                                coinListItem?.quote?.USD?.price!!,
                                                coinListItem.quote.USD.market_cap,
                                                coinListItem.quote.USD.percent_change_24h,
                                                coinListItem.quote.USD.percent_change_1h,
                                                coinListItem.quote.USD.percent_change_7d,
                                                coinListItem.quote.USD.percent_change_30d,
                                            )
                                        )
                                    }

                                },
                                modifier = Modifier.size((width * 0.07).dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Star,
                                    contentDescription = "",
                                    tint = if (coinDetailviewModel.star.value) StarCoinDetail else Color.Gray,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            IconButton(
                                onClick = {},
                                modifier = Modifier.size((width * 0.07).dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Share,
                                    contentDescription = "",
                                    tint = Color.Gray,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }

                    }

                    Spacer(modifier = Modifier.size((width * 0.1).dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .size((width * 0.9).dp),
                    ) {
                        item() {
                            Row(
                                modifier = Modifier
                                    .background(BackGround)
                                    .size((width * 1).dp, (width * 0.1).dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {

                                Text(
                                    text = "${coinListItem?.cmc_rank}. ",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                )

                                Image(
                                    painter = rememberAsyncImagePainter(coinDetail.logo),
                                    contentDescription = "",
                                    modifier = Modifier.size((width * 0.1).dp)
                                )
                                Spacer(modifier = Modifier.size(10.dp))

                                Text(
                                    text = "${coinDetail.name} (${coinDetail.symbol})",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                )
                            }
                            Spacer(modifier = Modifier.size((width * 0.1).dp))
                        }

                        item {

                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = coinDetail.description,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                )
                            }
                        }



                        item() {
                            Spacer(modifier = Modifier.size((width * 0.1).dp))

                            Text(
                                text = "Tags",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 20.sp
                            )

                            Spacer(modifier = Modifier.size(20.dp))

                            com.google.accompanist.flowlayout.FlowRow(
                                mainAxisSpacing = 10.dp,
                                crossAxisSpacing = 10.dp,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                coinDetail.tagNames.forEach { tag ->
                                    CoinTag(tag)
                                }
                            }

                        }


                        item() {
                            Spacer(modifier = Modifier.size(20.dp))

                            Divider()

                            Spacer(modifier = Modifier.size(20.dp))

                            Text(
                                text = "Официальные ссылки",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.size(10.dp))

                            if (coinDetail.urls.website.isNotEmpty()) {
                                com.google.accompanist.flowlayout.FlowRow(
                                    mainAxisSpacing = 10.dp,
                                    crossAxisSpacing = 10.dp,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    coinDetail.urls.website.forEach { websiteUrl ->
                                        WebSite(websiteUrl, width)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.size(10.dp))

                            if (coinDetail.urls.gitHub.isNotEmpty()) {
                                com.google.accompanist.flowlayout.FlowRow(
                                    mainAxisSpacing = 10.dp,
                                    crossAxisSpacing = 10.dp,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    coinDetail.urls.gitHub.forEach { githubUrl ->
                                        GitHub(githubUrl, width)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.size(10.dp))

                            if (coinDetail.urls.twitter.isNotEmpty()) {
                                com.google.accompanist.flowlayout.FlowRow(
                                    mainAxisSpacing = 10.dp,
                                    crossAxisSpacing = 10.dp,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    coinDetail.urls.documentation.forEach { docUrl ->
                                        WhitePaper(docUrl, width)
                                    }
                                }
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.size(20.dp))

                            Divider()

                            Spacer(modifier = Modifier.size(20.dp))

                            Text(
                                text = "Социальные сети",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.size(10.dp))

                            Row(modifier = Modifier.fillMaxWidth()) {
                                if (coinDetail.urls.reddit.isNotEmpty()) {
                                    Reddit(coinDetail.urls.reddit[0], width)
                                }

                                Spacer(modifier = Modifier.size(10.dp))

                                if (coinDetail.urls.twitter.isNotEmpty()) {
                                    Twitter(coinDetail.urls.twitter[0], width)
                                }

                                if (coinDetail.urls.facebook.isNotEmpty()) {
                                    Facebook(coinDetail.urls.facebook[0], width)
                                }

                            }

                            com.google.accompanist.flowlayout.FlowRow(
                                mainAxisSpacing = 10.dp,
                                crossAxisSpacing = 10.dp,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                coinDetail.urls.message_board.forEach { chatUrl ->
                                    Chat(chatUrl, width)
                                }
                            }
                        }

                    }
                }
            }


            if (coinDetail.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            if (coinDetail.error?.isNotBlank() == true) {
                coinDetail.error?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
            }
        }
    }

}