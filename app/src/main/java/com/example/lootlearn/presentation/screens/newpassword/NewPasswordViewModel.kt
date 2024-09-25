package com.example.lootlearn.presentation.screens.newpassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NewPasswordViewModel @Inject constructor(): ViewModel() {

    private val _newPasswordText = mutableStateOf("")
    val newPasswordText: State<String> = _newPasswordText

    private val _showNewPassword = mutableStateOf(false)
    val showNewPassword: State<Boolean> = _showNewPassword

    private val _confirmPasswordText = mutableStateOf("")
    val confirmPasswordText: State<String> = _confirmPasswordText

    private val _showConfirmPassword = mutableStateOf(false)
    val showConfirmPassword: State<Boolean> = _showConfirmPassword

    fun setNewPasswordText(password: String){
        _newPasswordText.value = password
    }

    fun setShowNewPassword(showPassword: Boolean) {
        _showNewPassword.value = showPassword
    }

    fun setConfirmPasswordText(password: String){
        _confirmPasswordText.value = password
    }

    fun setShowConfirmPassword(showPassword: Boolean) {
        _showConfirmPassword.value = showPassword
    }

}