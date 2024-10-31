package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.exchange_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoExchanges(width: Int) {

    Box(modifier = Modifier.fillMaxSize().padding(start = (width * 0.05).dp),
        contentAlignment = Alignment.CenterStart
        ) {
        Text(text = "Биржи",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            fontSize = 14.sp
        )
    }

}