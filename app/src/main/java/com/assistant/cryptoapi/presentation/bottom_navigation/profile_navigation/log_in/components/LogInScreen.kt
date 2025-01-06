package com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation.log_in.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation.ProfileViewModel
import com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation.log_in.LogInViewModel
import com.assistant.cryptoapi.presentation.navigation.Screen
import com.assistant.cryptoapi.presentation.ui.theme.BackGround
import com.assistant.cryptoapi.presentation.ui.theme.BackGroundBottomNav
import com.assistant.cryptoapi.presentation.ui.theme.CastomBlue
import com.assistant.cryptoapi.presentation.ui.theme.CastomBlueLighter
import com.assistant.cryptoapi.presentation.ui.theme.CastomGray
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    width: Int, height: Int,
    navController: NavController,
    logInViewModel: LogInViewModel = hiltViewModel(),
) {

    val userList = logInViewModel.userList.collectAsState(emptyList())

    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(modifier = Modifier.background(BackGround)) {

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                containerColor = BackGroundBottomNav,
                tonalElevation = 16.dp,
                dragHandle = {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(50.dp)
                            .height(6.dp)
                            .clip(RoundedCornerShape(50))
                            .background(MaterialTheme.colorScheme.outline)
                    )
                }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                        ) {
                        Icon(
                            imageVector = Icons.Filled.Dangerous,
                            contentDescription = "Favorite Icon",
                            tint = Color.Red,
                            modifier = Modifier.size((width * 0.08).dp)
                        )

                        Spacer(modifier = Modifier.size((width * 0.02).dp))

                        Text(
                            "Неверные учетные данные",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500
                        )
                    }

                    Spacer(modifier = Modifier.size((width * 0.05).dp))

                    Text(
                        "Ваши адрес эл. почты и пароль не совпадают.\nПожалуйста, проверьте и попробуйте еще раз",
                        color = MaterialTheme.colorScheme.outline,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                       showBottomSheet = false
                    },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.size((width * 1).dp, (width * 0.12).dp),
                        colors = ButtonDefaults.buttonColors(containerColor = CastomBlue)
                    ) {
                        Text(text = "Попробуйте еще раз",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.size((width * 0.9).dp, (height * 0.06).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.BottomNavigationScreen.route)
                                  },
                        modifier = Modifier.size((width * 0.1).dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Favorite Icon",
                            tint = Color.Gray,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.size((width * 0.05).dp))

                    Text(
                        text = "Войти",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.White
                    )
                }

                Text(
                    text = "Регистрация",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = CastomBlue,
                    modifier = Modifier.clickable {
                        logInViewModel.onMailChange("")
                        logInViewModel.onPasChange("")

                        navController.navigate(Screen.RegisterScreen.route)
                    }
                )
            }

            Column(modifier = Modifier.size((width * 0.9).dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
                ) {
                Text(
                    text = "Адрес эл. почты",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.White
                )

                Spacer(modifier = Modifier.size(((width * 0.03).dp)))

                OutlinedTextField(
                    logInViewModel.mail.value,
                    {
                        logInViewModel.onMailChange(it)
                        logInViewModel.onMailIsNull(false)
                    },
                    textStyle = TextStyle(fontSize = 14.sp),
                    placeholder = { Text(text = "Введите ваш адрес эл. почты...") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CastomBlue,
                        focusedLabelColor = Color.White,
                        unfocusedPlaceholderColor = Color.Gray,
                        cursorColor = CastomBlue,
                        selectionColors = TextSelectionColors(handleColor = CastomBlueLighter,
                            backgroundColor = Color.Black),
                        errorBorderColor = Color.Red,
                        errorCursorColor = Color.Red,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.size((width * 1).dp, (width * 0.15).dp),
                    isError = logInViewModel.mailIsNull.value,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    ),
                )
                if (logInViewModel.mailIsNull.value) {
                    Text(
                        text = "Поле адреса эл. почты не может быть пустым",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Red
                    )
                }

                if (!logInViewModel.mailIsNull.value) Spacer(modifier = Modifier.size(((width * 0.1).dp)))

                Text(
                    text = "Пароль",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.White
                )

                Spacer(modifier = Modifier.size(((width * 0.03).dp)))

                OutlinedTextField(
                    logInViewModel.pas.value,
                    {
                        logInViewModel.onPasChange(it)
                        logInViewModel.onPasIsNull(false)
                    },
                    textStyle = TextStyle(fontSize = 14.sp),
                    placeholder = { Text(text = "Введите свой пароль") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CastomBlue,
                        focusedLabelColor = Color.White,
                        unfocusedPlaceholderColor = Color.Gray,
                        cursorColor = CastomBlue,
                        selectionColors = TextSelectionColors(handleColor = CastomBlueLighter,
                            backgroundColor = Color.Black),
                        errorCursorColor = Color.Red,
                        errorBorderColor = Color.Red,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.size((width * 1).dp, (width * 0.15).dp),
                    isError = logInViewModel.pasIsNull.value,
                    trailingIcon = {
                        IconButton(
                            onClick = { logInViewModel.togglePasswordVisibility() },
                            modifier = Modifier.size((width * 0.05).dp)
                        ) {
                            Icon(
                                imageVector = if (logInViewModel.isPasswordVisible.value) {
                                    Icons.Default.Visibility // Открытый глаз
                                } else {
                                    Icons.Default.VisibilityOff // Закрытый глаз
                                },
                                contentDescription = if (logInViewModel.isPasswordVisible.value) {
                                    "Скрыть пароль"
                                } else {
                                    "Показать пароль"
                                },
                                modifier = Modifier.size((width * 0.08).dp)
                            )
                        }
                    },
                    visualTransformation = if (logInViewModel.isPasswordVisible.value) {
                        VisualTransformation.None // Пароль отображается
                    } else {
                        PasswordVisualTransformation() // Пароль скрыт
                    },
                    singleLine = true

                )
                if (logInViewModel.pasIsNull.value) {
                    Text(
                        text = "Поле ввода пароля не может быть пустым",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Red
                    )
                }

                Spacer(modifier = Modifier.size(((width * 0.1).dp)))

                Button(onClick = {

                    if (logInViewModel.mail.value == "") {
                        logInViewModel.onMailIsNull(true)
                    }

                    if (logInViewModel.pas.value == "") {
                        logInViewModel.onPasIsNull(true)
                    }

                    if (logInViewModel.mail.value != "" && logInViewModel.pas.value != "") {

                        if (logInViewModel.checkCredentials(userList.value, logInViewModel.mail.value, logInViewModel.pas.value)) {

                            logInViewModel.onMailSave()
                            logInViewModel.onPasSave()

                            Toast.makeText(context, "Вы успешно зашли", Toast.LENGTH_SHORT).show()

                            navController.navigate(Screen.BottomNavigationScreen.route)
                        }
                        else {
                            coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) { showBottomSheet = true }
                            }
                        }

                    }


                },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.size((width * 1).dp, (width * 0.12).dp),
                    colors = ButtonDefaults.buttonColors(containerColor =
                    if (logInViewModel.mail.value != "" && logInViewModel.pas.value != "") CastomBlue else CastomGray
                    )
                    ) {
                    Text(text = "Войти",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.LightGray
                        )
                }

            }

            Row(modifier = Modifier.size((width * 0.9).dp, (width * 0.1).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
                ) {
                Text(
                    text = "Впервые тут ? ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Gray
                )

                Text(
                    text = "Создать аккаунт",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = CastomBlue,
                    modifier = Modifier.clickable {
                        logInViewModel.onMailChange("")
                        logInViewModel.onPasChange("")

                        navController.navigate(Screen.RegisterScreen.route)
                    }
                )
            }

        }
    }
}