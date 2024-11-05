package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.global_metrics.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.assistant.cryptoapi.R
import com.assistant.cryptoapi.presentation.Screen
import com.assistant.cryptoapi.presentation.bottom_navigation.BottomNavigationViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.CoinListItem
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components.formatFloat
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.global_metrics.GlobalMetricsViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.search_navigation.SearchViewModel
import com.assistant.cryptoapi.presentation.ui.theme.BackGroundBottomNav

@Composable
fun GlobalMetrics(
    width: Int,
    bottomNavController: NavController,
    viewmodel: GlobalMetricsViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel(),
    bottomNavigationViewModel: BottomNavigationViewModel = hiltViewModel()
    ) {

    val globalMetricsState = viewmodel.globalMetricsState.value

    Log.d("state22", globalMetricsState.toString())
    Box(modifier = Modifier.fillMaxSize()) {
        globalMetricsState.coin?.let { coin ->
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Row(modifier = Modifier
                    .size((width * 1).dp, (width * 0.1).dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    Text("Рынок",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                        )

                    Row(modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                        IconButton(onClick = {
                            bottomNavController.navigate(Screen.BottomNavigationSearchScreen.route)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Favorite Icon",
                                tint = Color.Gray,
                                modifier = Modifier.size((width * 0.08).dp)
                            )
                        }

                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Favorite Icon",
                                tint = Color.Gray,
                                modifier = Modifier.size((width * 0.08).dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(10.dp))

                Card(modifier = Modifier
                    .size((width * 1).dp, (width * 0.22).dp)
                ) {
                    Row(modifier = Modifier.fillMaxSize()
                        .background(BackGroundBottomNav),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                        Box(modifier = Modifier
                            .size((width * 0.22).dp)
                            .padding(start = 5.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.Start
                                ) {
                                Text(text = "Рын.кап.",
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    fontSize = 10.sp,
                                    )

                                Text(text = "$${viewmodel.formatLargeCurrency(coin.data.quote.USD.total_market_cap)}",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                )

                                Row(modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                    ) {
                                    if (viewmodel.formatFloat(coin.data.quote.USD.total_market_cap_yesterday_percentage_change) != "0") {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowDropDown,
                                            contentDescription = "Favorite Icon",
                                            tint = if (coin.data.quote.USD.total_market_cap_yesterday_percentage_change < 0) Color.Red else Color.Green,
                                            modifier = Modifier
                                                .rotate(if (coin.data.quote.USD.total_market_cap_yesterday_percentage_change < 0) 180f else 0f)
                                                .size((width * 0.05).dp)
                                        )
                                    }

                                    Text(
                                        text = "${viewmodel.formatFloat(coin.data.quote.USD.total_market_cap_yesterday_percentage_change)}%",
                                        color =
                                        if (viewmodel.formatFloat(coin.data.quote.USD.total_market_cap_yesterday_percentage_change) == "0") Color.Gray
                                        else if (coin.data.quote.USD.total_market_cap_yesterday_percentage_change < 0) Color.Red
                                        else Color.Green,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Italic,
                                    )
                                }
                            }
                        }

                        Box(modifier = Modifier.size(1.dp, (width * 1).dp).background(Color.Gray))

                        Box(modifier = Modifier
                            .size((width * 0.22).dp)
                            .padding(start = 5.dp)

                        ) {
                            Column(modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "Объем",
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    fontSize = 10.sp,
                                )

                                Text(text = "$${viewmodel.formatLargeCurrency(coin.data.quote.USD.total_volume_24h)}",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                )

                                Row(modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    if (viewmodel.formatFloat(coin.data.quote.USD.total_volume_24h_yesterday_percentage_change) != "0") {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowDropDown,
                                            contentDescription = "Favorite Icon",
                                            tint = if (coin.data.quote.USD.total_volume_24h_yesterday_percentage_change < 0) Color.Red else Color.Green,
                                            modifier = Modifier
                                                .rotate(if (coin.data.quote.USD.total_volume_24h_yesterday_percentage_change < 0) 180f else 0f)
                                                .size((width * 0.05).dp)
                                        )
                                    }

                                    Text(
                                        text = "${viewmodel.formatFloat(coin.data.quote.USD.total_volume_24h_yesterday_percentage_change)}%",
                                        color =
                                        if (viewmodel.formatFloat(coin.data.quote.USD.total_volume_24h_yesterday_percentage_change) == "0") Color.Gray
                                        else if (coin.data.quote.USD.total_volume_24h_yesterday_percentage_change < 0) Color.Red
                                        else Color.Green,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Italic,
                                    )
                                }
                            }
                        }

                        Box(modifier = Modifier.size(1.dp, (width * 1).dp).background(Color.Gray))

                        Box(modifier = Modifier
                            .size((width * 0.22).dp)
                            .padding(start = 5.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "Доминация",
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    fontSize = 10.sp,
                                )

                                Text(text = "$${viewmodel.formatFloat(coin.data.btc_dominance)}%",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                )

                                Row(modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Image(painterResource(R.drawable.logo_bitcoin), contentDescription = "",
                                        modifier = Modifier.size((width * 0.04).dp)
                                        )

                                    Text(
                                        text = "BTC",
                                        color = Color.Gray,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Italic,
                                        fontFamily = FontFamily.Serif
                                    )
                                }
                            }
                        }

                        Box(modifier = Modifier.size(1.dp, (width * 1).dp).background(Color.Gray))

                        Box(modifier = Modifier
                            .size((width * 0.22).dp)
                            .padding(start = 5.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "Доминация",
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    fontSize = 10.sp,
                                )

                                Text(text = "$${viewmodel.formatFloat(coin.data.eth_dominance)}%",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                )

                                Row(modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Image(painterResource(R.drawable.logo_ethir), contentDescription = "",
                                        modifier = Modifier.size((width * 0.04).dp)
                                    )

                                    Text(
                                        text = "ETH",
                                        color = Color.Gray,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Italic,
                                        fontFamily = FontFamily.Serif
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }


        if (globalMetricsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (globalMetricsState.error.isNotBlank()) {
            Text(
                text = globalMetricsState.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding (horizontal = 20. dp)
                    .align (Alignment. Center)
            )
        }
    }

}