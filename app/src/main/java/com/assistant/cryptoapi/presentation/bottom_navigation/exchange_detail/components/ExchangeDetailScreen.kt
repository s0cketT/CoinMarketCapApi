package com.assistant.cryptoapi.presentation.bottom_navigation.exchange_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.assistant.cryptoapi.presentation.bottom_navigation.exchange_detail.ExchangeDetailViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_detail.components.officia_links.WebSite
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_detail.components.social_networks.Chat
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_detail.components.social_networks.Twitter
import com.assistant.cryptoapi.presentation.ui.theme.BackGround

@Composable
fun ExchangeDetailScreen(
    width: Int,
    height: Int,
    navController: NavController,
    exchangeDetailviewModel: ExchangeDetailViewModel = hiltViewModel(),
) {

    val exchangeDetail = exchangeDetailviewModel.state.value

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
            exchangeDetail.exchange?.let { exchangeDetail ->
                Box(
                    modifier = Modifier.size((width * 1).dp, (width * 0.1).dp),
                    contentAlignment = Alignment.CenterStart) {

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

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(exchangeDetail.logo),
                                contentDescription = "",
                                modifier = Modifier.size((width * 0.08).dp)
                            )

                            Spacer(modifier = Modifier.size(5.dp))

                            Text(
                                text = exchangeDetail.name,
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
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
                            Image(
                                painter = rememberAsyncImagePainter(exchangeDetail.logo),
                                contentDescription = "",
                                modifier = Modifier.size((width * 0.1).dp)
                            )

                            Spacer(modifier = Modifier.size(10.dp))

                            Text(
                                text = "${exchangeDetail.name}",
                                color = Color.White,
                                fontSize = 24.sp,
                            )
                        }
                        Spacer(modifier = Modifier.size((width * 0.1).dp))
                    }

                    item {

                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = exchangeDetail.description,
                                color = Color.White,
                                fontSize = 14.sp,
                            )
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


                            Row(
                                modifier = Modifier
                                    .size((width * 1).dp, (width * 0.15).dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                if (exchangeDetail.urls.website.isNotEmpty()) {
                                    WebSite(exchangeDetail.urls.website[0], width)
                                }

                                if (exchangeDetail.urls.twitter.isNotEmpty()) {
                                    Twitter(exchangeDetail.urls.website[0], width)
                                }

                                if (exchangeDetail.urls.chat.isNotEmpty()) {
                                    Chat(exchangeDetail.urls.website[0], width)
                                }
                            }

                    }
                }

            }
        }


        if (exchangeDetail.isLoading) {
            CircularProgressIndicator()
        }
        if (exchangeDetail.error?.isNotBlank() == true) {
            exchangeDetail.error?.let {
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