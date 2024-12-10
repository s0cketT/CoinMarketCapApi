package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assistant.cryptoapi.R
import com.assistant.cryptoapi.domain.model.Coin
import com.assistant.cryptoapi.domain.model.CoinListResponse
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.CoinDetailViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.HourCoinsItem
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CoinListItem(
    coin: CoinListResponse, onItemClick: (String) -> Unit,
    viewModel: CoinListViewModel = hiltViewModel(),
) {

    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp


    Row(modifier = Modifier
        .size((width * 1).dp, (width * 0.13).dp)
        .clickable {
            onItemClick(coin.id)

                   },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier
            .size((width * 0.68).dp, (width * 0.13).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "${coin.cmc_rank}",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                fontWeight = FontWeight.Bold,
                fontFamily=FontFamily.Serif
                )

            Column(modifier = Modifier
                .size((width * 0.21).dp, (width * 0.13).dp)
            ) {
                Text(text = coin.symbol,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily=FontFamily.Serif
                    )

                Text(text = "$${viewModel.formatLargeCurrency(coin.quote.USD.market_cap)}",
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    fontFamily=FontFamily.Serif
                    )

            }

            Box(modifier = Modifier
                .size((width * 0.3).dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = "$${viewModel.formatNumber(coin.quote.USD.price.toDouble())}",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily=FontFamily.Serif
                )
            }

        }

        Row(modifier = Modifier
            .size((width * 0.2).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            if (viewModel.hourCount.value == 24) {
                if (viewModel.formatFloat(coin.quote.USD.percent_change_24h) != "0") {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Favorite Icon",
                        tint = if (coin.quote.USD.percent_change_24h < 0) Color.Red else Color.Green,
                        modifier = Modifier
                            .rotate(if (coin.quote.USD.percent_change_24h < 0) 0f else 180f)
                            .size((width * 0.05).dp)
                    )
                }

                Text(
                    text = "${viewModel.formatFloat(coin.quote.USD.percent_change_24h)}%",
                    color = if (viewModel.formatFloat(coin.quote.USD.percent_change_24h) == "0") Color.Gray
                    else if (coin.quote.USD.percent_change_24h < 0) Color.Red
                    else Color.Green,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif
                )
            }
            else if (viewModel.hourCount.value == 1) {
                if (viewModel.formatFloat(coin.quote.USD.percent_change_1h) != "0") {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Favorite Icon",
                        tint = if (coin.quote.USD.percent_change_1h < 0) Color.Red else Color.Green,
                        modifier = Modifier
                            .rotate(if (coin.quote.USD.percent_change_1h < 0) 0f else 180f)
                            .size((width * 0.05).dp)
                    )
                }

                Text(
                    text = "${viewModel.formatFloat(coin.quote.USD.percent_change_1h)}%",
                    color = if (viewModel.formatFloat(coin.quote.USD.percent_change_1h) == "0") Color.Gray
                    else if (coin.quote.USD.percent_change_1h < 0) Color.Red
                    else Color.Green,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif
                )
            }
            else if (viewModel.hourCount.value == 7) {
                if (viewModel.formatFloat(coin.quote.USD.percent_change_7d) != "0") {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Favorite Icon",
                        tint = if (coin.quote.USD.percent_change_7d < 0) Color.Red else Color.Green,
                        modifier = Modifier
                            .rotate(if (coin.quote.USD.percent_change_7d < 0) 0f else 180f)
                            .size((width * 0.05).dp)
                    )
                }

                Text(
                    text = "${viewModel.formatFloat(coin.quote.USD.percent_change_7d)}%",
                    color = if (viewModel.formatFloat(coin.quote.USD.percent_change_7d) == "0") Color.Gray
                    else if (coin.quote.USD.percent_change_7d < 0) Color.Red
                    else Color.Green,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif
                )
            }
            else {
                if (viewModel.formatFloat(coin.quote.USD.percent_change_30d) != "0") {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Favorite Icon",
                        tint = if (coin.quote.USD.percent_change_30d < 0) Color.Red else Color.Green,
                        modifier = Modifier
                            .rotate(if (coin.quote.USD.percent_change_30d < 0) 0f else 180f)
                            .size((width * 0.05).dp)
                    )
                }

                Text(
                    text = "${viewModel.formatFloat(coin.quote.USD.percent_change_30d)}%",
                    color = if (viewModel.formatFloat(coin.quote.USD.percent_change_30d) == "0") Color.Gray
                    else if (coin.quote.USD.percent_change_30d < 0) Color.Red
                    else Color.Green,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif
                )
            }
        }

    }
}
