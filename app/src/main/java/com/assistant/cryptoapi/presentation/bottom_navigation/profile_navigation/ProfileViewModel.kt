package com.assistant.cryptoapi.presentation.bottom_navigation.profile_navigation

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
class ProfileViewModel @Inject constructor(private val database: MainDatabase, private val userRepository: UserRepository) : ViewModel() {

    val userList = database.daoUser.getAllUsers()

    private val _mail = mutableStateOf("")
    val mail: State<String> = _mail
    fun onMailChange(name: String) { _mail.value = name }

    fun onMailSave() { userRepository.mail = mail.value }
    fun onCheckUser(): Boolean { return userRepository.mail == null }

    private val _mailIsNull = mutableStateOf(false)
    val mailIsNull: State<Boolean> = _mailIsNull
    fun onMailIsNull(mailisNull: Boolean) { _mailIsNull.value = mailisNull }

    private val _pasIsNull = mutableStateOf(false)
    val pasIsNull: State<Boolean> = _pasIsNull
    fun onPasIsNull(pasisNull: Boolean) { _pasIsNull.value = pasisNull }


    private val _pas = mutableStateOf("")
    val pas: State<String> = _pas
    fun onPasChange(pas: String) { _pas.value = pas }
    fun onPasSave() { userRepository.pas = pas.value }

    val isPasswordVisible = mutableStateOf(false)
    fun togglePasswordVisibility() {
        isPasswordVisible.value = !isPasswordVisible.value
    }


    fun insertUser(user: UserDB) {
        viewModelScope.launch {
            database.daoUser.insertUser(user)
        }
    }

    fun updateUser(user: UserDB) {
        viewModelScope.launch {
            database.daoUser.updateUser(user)
        }
    }

    fun deleteUser(user: UserDB) {
        viewModelScope.launch {
            database.daoUser.deleteUser(user)
        }
    }


    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
        )
        return emailRegex.matches(email)
    }

    fun isValidPassword(password: String): Boolean {
        // Проверяем длину пароля
        if (password.length < 8) return false

        // Проверяем, содержит ли пароль хотя бы одну букву и одну цифру
        val containsLetter = password.any { it.isLetter() }
        val containsDigit = password.any { it.isDigit() }

        return containsLetter && containsDigit
    }
}