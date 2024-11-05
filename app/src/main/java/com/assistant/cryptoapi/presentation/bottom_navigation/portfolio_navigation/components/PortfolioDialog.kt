package com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.PortfolioViewModel
import com.assistant.cryptoapi.presentation.ui.theme.BackGroundBottomNav
import com.catching.pucks.database.DataBase.CoinPortfolioDB

@Composable
fun PortfolioDialog(
    width: Int,
    portfolioViewModel: PortfolioViewModel = hiltViewModel()
) {

    val list = portfolioViewModel.list.collectAsState(emptyList())


    AlertDialog(
        onDismissRequest = { portfolioViewModel.changeIsVisible() },
        containerColor = BackGroundBottomNav,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Filled.Build, contentDescription = "", tint = Color.Gray)

                Spacer(modifier = Modifier.size((width * 0.05).dp))

                Text(
                    text = "Добавить ${portfolioViewModel.coinClickSymbol.value}",
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
        },
        text = {
            AddNewItemDialog(width)
        },
        confirmButton = {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    {
                        portfolioViewModel.onTextCount("")
                        portfolioViewModel.changeIsVisible()
                    },
                    modifier = Modifier.size((width * 0.2).dp, (width * 0.1).dp)
                ) {
                    Text("Отмена", fontSize = 15.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.size((width * 0.05).dp))

                IconButton(
                    onClick = {
                        portfolioViewModel.insertCoin(
                            CoinPortfolioDB(
                                0,
                                portfolioViewModel.coinClick.value.id.toInt(),
                                portfolioViewModel.coinClick.value.name,
                                portfolioViewModel.coinClick.value.cmc_rank,
                                portfolioViewModel.coinClick.value.symbol,
                                (portfolioViewModel.textPrice.value.toDouble() / portfolioViewModel.textCount.value.toDouble()).toString(),
                                portfolioViewModel.textCount.value.toDouble(),
                                portfolioViewModel.textPrice.value.toDouble(),
                            )
                        )
                    },
                    modifier = Modifier.size((width * 0.2).dp, (width * 0.1).dp)
                ) {
                    Text("Добавить", fontSize = 15.sp, color = Color.White)
                }
            }


        },
    )

}

