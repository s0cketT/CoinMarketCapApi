package com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.portfolio_navigation.PortfolioViewModel

@Composable
fun AddNewItemDialog(width: Int, portfolioViewModel: PortfolioViewModel = hiltViewModel()) {

    Row {
        Column(modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = portfolioViewModel.textCount.value,
                onValueChange = {
                    if (portfolioViewModel.isValidNumber(it)) {
                        portfolioViewModel.onTextCount(it) }
                },
                modifier = Modifier
                    .size((width * 0.35).dp, (width * 0.15).dp)
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(50.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Blue,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    if (portfolioViewModel.textCount.value != "") {
                        IconButton(
                            onClick = { portfolioViewModel.onTextCount("") },
                            modifier = Modifier
                                .size((width * 0.06).dp)
                        ) {
                            Icon(
                                Icons.Outlined.Close, contentDescription = null, tint = Color.DarkGray,
                                modifier = Modifier.size((width * 0.04).dp)
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color.Gray)
                            )
                        }
                    }
                },
                placeholder = {

                    Text(text = "Кол.")

                },

                shape = RoundedCornerShape(50)
            )
        }

        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            OutlinedTextField(
                value = portfolioViewModel.textPrice.value,
                onValueChange = {
                    if (portfolioViewModel.isValidNumber(it)) {
                        portfolioViewModel.onTextPrice(it)
                    }
                },
                modifier = Modifier
                    .size((width * 0.35).dp, (width * 0.15).dp)
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(50.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Blue,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    if (portfolioViewModel.textPrice.value != "") {
                        IconButton(
                            onClick = { portfolioViewModel.onTextPrice("") },
                            modifier = Modifier
                                .size((width * 0.06).dp)
                        ) {
                            Icon(
                                Icons.Outlined.Close, contentDescription = null, tint = Color.DarkGray,
                                modifier = Modifier.size((width * 0.04).dp)
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color.Gray)
                            )
                        }
                    }
                },
                placeholder = {

                    Text(text = "Цена")

                },

                shape = RoundedCornerShape(50)
            )
        }
    }

}