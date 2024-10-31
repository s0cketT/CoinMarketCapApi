package com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.CoinListViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.home_navigation.coin_list.TopCoinsItem
import com.assistant.cryptoapi.presentation.ui.theme.Pink40
import com.assistant.cryptoapi.presentation.ui.theme.TopCoinsColor

@Composable
fun TopCoins(
    width: Int,
    dropdownItems: List<TopCoinsItem>,
    onItemClick: (TopCoinsItem) -> Unit,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier.size((width * 0.2).dp, (width * 0.08).dp)) {
        Button(
            onClick = {
                isContextMenuVisible = true
            },
            modifier = Modifier
                .pointerInput(true) {
                    detectTapGestures(
                        onPress = {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                        }
                    )
                }
                .fillMaxSize(),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(TopCoinsColor),
            contentPadding = PaddingValues(0.dp)
        ) {
            Row(modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Топ ${viewModel.limit.value}",
                    color = Color.White,
                    fontSize = 14.sp
                )

                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Favorite Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size((width * 0.05).dp)
                )
            }
        }

        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false },
            offset = pressOffset,
            modifier = Modifier
                .size((width * 0.2).dp, (width * 0.4).dp)
                .background(TopCoinsColor)
        ) {
            dropdownItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.text) },
                    onClick = {
                        viewModel.changeLimitCoin(item.count)
                        onItemClick(item)
                        isContextMenuVisible = false
                    },
                    colors = MenuDefaults.itemColors(textColor = Color.White)
                    )

            }
        }
    }
}
