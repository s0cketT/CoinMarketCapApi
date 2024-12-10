package com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
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
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.PortfolioViewModel

@Composable
fun PortfolioSearchCoinListItem(
    coin: CoinListResponse,
    width: Int,
    portfolioViewModel: PortfolioViewModel = hiltViewModel(),
    viewModel: CoinListViewModel = hiltViewModel(),
) {

    Row(
        modifier = Modifier
            .size((width * 0.9).dp, (width * 0.1).dp)
            .clickable {
                portfolioViewModel.onTextPrice(viewModel.formatFloat(coin.quote.USD.price.toDouble()))
                portfolioViewModel.coinClickSymbol.value = coin.symbol
                portfolioViewModel.coinClick.value = coin
                portfolioViewModel.changeIsVisible()
                       },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Text(
            text = coin.symbol,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.size(5.dp))

        Text(
            text = coin.name,
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontFamily.Serif
        )


    }

}

