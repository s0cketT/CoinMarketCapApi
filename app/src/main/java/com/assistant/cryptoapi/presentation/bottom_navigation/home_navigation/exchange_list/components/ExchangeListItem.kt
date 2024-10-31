package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assistant.cryptoapi.domain.model.ExchangeListResponse
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.ExchangeListViewModel

@Composable
fun ExchangeListItem(
    width: Int,
    height: Int,
    exchange: ExchangeListResponse,
    onItemClick: (String) -> Unit,
    viewModel: ExchangeListViewModel = hiltViewModel()
) {

    Box(modifier = Modifier.fillMaxSize().padding(start = (width * 0.05).dp)
        .clickable { onItemClick(exchange.id) },
        contentAlignment = Alignment.CenterStart
        ) {

        Text(text = exchange.name,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily= FontFamily.Serif
        )



    }

}