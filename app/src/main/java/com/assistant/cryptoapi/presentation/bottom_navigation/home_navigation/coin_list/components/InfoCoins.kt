package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel

@Composable
fun InfoCoins(
    width: Int,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {  },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.size((width * 0.68).dp, (width * 0.1).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .size((width * 0.1).dp, (width * 0.13).dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() })
                    { viewModel.isIndex(1) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "#",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )

                if (viewModel.activeIndex.value == 1) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Favorite Icon",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size((width * 0.08).dp)
                            .rotate(if (viewModel.arrow.value) 180f else 0f)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() })
                            { viewModel.isArrow() }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .size((width * 0.3).dp, (width * 0.13).dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() })
                    { viewModel.isIndex(2) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Рын.кап.",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )

                if (viewModel.activeIndex.value == 2) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Favorite Icon",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size((width * 0.08).dp)
                            .rotate(if (viewModel.arrow.value) 180f else 0f)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() })
                            { viewModel.isArrow() }
                    )
                }

            }

            Box(
                modifier = Modifier
                    .size((width * 0.3).dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() })
                        { viewModel.isIndex(3) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {


                    Text(
                        text = "Цена",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        fontSize = 14.sp
                    )

                    if (viewModel.activeIndex.value == 3) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Favorite Icon",
                            tint = Color.Blue,
                            modifier = Modifier
                                .size((width * 0.08).dp)
                                .rotate(if (viewModel.arrow.value) 180f else 0f)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() })
                                { viewModel.isArrow() }
                        )
                    }
                }
            }
        }


        Box(
            modifier = Modifier.size((width * 0.25).dp, (width * 0.1).dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() })
                    { viewModel.isIndex(4) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = viewModel.hour.value,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )

                if (viewModel.activeIndex.value == 4) {

                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Favorite Icon",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size((width * 0.08).dp)
                            .rotate(if (viewModel.arrow.value) 180f else 0f)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() })
                            { viewModel.isArrow() }
                    )
                }

            }
        }

    }
}