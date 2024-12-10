package com.assistant.cryptoapi.presentation.bottom_navigation.search_navigation.components

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assistant.cryptoapi.domain.model.CoinListResponse
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel

@Composable
fun CoinSearchListItem(
    coin: CoinListResponse,
    width: Int,
    onItemClick: (String) -> Unit,
    viewModel: CoinListViewModel = hiltViewModel(),
) {

    Row(
        modifier = Modifier.size((width * 1).dp, (width * 0.1).dp)
            .clickable { onItemClick(coin.id) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(modifier = Modifier.size((width * 0.6).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Text(
                text = coin.symbol,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )


            Text(
                text = "$${viewModel.formatNumber(coin.quote.USD.price.toDouble())}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }


        Row(
            modifier = Modifier
                .size((width * 0.2).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            if (viewModel.formatFloat(coin.quote.USD.percent_change_24h) != "0") {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Favorite Icon",
                    tint = if (coin.quote.USD.percent_change_24h < 0) Color.Red else Color.Green,
                    modifier = Modifier
                        .rotate(if (coin.quote.USD.percent_change_24h < 0) 0f else 0f)
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

    }

}

