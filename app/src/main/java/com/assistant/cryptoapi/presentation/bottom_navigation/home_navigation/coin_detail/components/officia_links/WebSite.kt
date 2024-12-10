package com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.components.officia_links

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assistant.cryptoapi.R
import com.assistant.cryptoapi.presentation.bottom_navigation.coin_detail.CoinDetailViewModel
import com.assistant.cryptoapi.presentation.ui.theme.BackGroundBottomNav

@Composable
fun WebSite(
    websiteUrl: String,
    width: Int,
    coinDetailViewModel: CoinDetailViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    SuggestionChip(
        onClick = { coinDetailViewModel.openLink(websiteUrl, context) },
        label = { Text("Веб-сайт",
            fontSize = 14.sp,
            color = Color.Gray,
        ) },
        icon = {
            Icon(painter = painterResource(R.drawable.website_icon), contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.size((width * 0.05).dp)
            )
        },
        shape = RoundedCornerShape(100.dp),
        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = BackGroundBottomNav)
    )
}
