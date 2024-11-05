package com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoinTag(
    tag: String
) {
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = Color.Green,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tag,
            color = Color.Green,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}