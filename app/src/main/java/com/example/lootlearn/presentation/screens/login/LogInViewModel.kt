package com.example.lootlearn.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LogInViewModel @Inject constructor(): ViewModel(){

    private val _useridText = mutableStateOf("")
    val useridText: State<String> = _useridText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    fun setUserIdText(userid: String){
        _useridText.value = userid
    }

    fun setPasswordText(password: String){
        _passwordText.value = password
    }

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }
}