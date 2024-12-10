package com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation.log_in

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.cryptoapi.domain.repository.UserRepository
import com.catching.pucks.database.DataBase.CoinDB
import com.catching.pucks.database.DataBase.MainDatabase
import com.catching.pucks.database.DataBase.UserDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val database: MainDatabase, private val userRepository: UserRepository) : ViewModel() {

    val userList = database.daoUser.getAllUsers()

    private val _mail = mutableStateOf("")
    val mail: State<String> = _mail
    fun onMailChange(name: String) { _mail.value = name }

    fun onMailSave() { userRepository.mail = mail.value }

    private val _mailIsNull = mutableStateOf(false)
    val mailIsNull: State<Boolean> = _mailIsNull
    fun onMailIsNull(mailisNull: Boolean) { _mailIsNull.value = mailisNull }

    private val _pasIsNull = mutableStateOf(false)
    val pasIsNull: State<Boolean> = _pasIsNull
    fun onPasIsNull(pasisNull: Boolean) { _pasIsNull.value = pasisNull }

    fun onPasSave() { userRepository.pas = pas.value }

    private val _pas = mutableStateOf("")
    val pas: State<String> = _pas
    fun onPasChange(pas: String) { _pas.value = pas }

    val isPasswordVisible = mutableStateOf(false)
    fun togglePasswordVisibility() {
        isPasswordVisible.value = !isPasswordVisible.value
    }


    fun checkCredentials(users: List<UserDB>, mail: String, password: String): Boolean {
        return users.any { user ->
            user.login == mail && user.password == password
        }
    }

}