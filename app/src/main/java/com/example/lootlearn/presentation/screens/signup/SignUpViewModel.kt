package com.example.lootlearn.presentation.screens.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {

    private val _useridText = mutableStateOf("")
    val useridText: State<String> = _useridText

    private val _fullNameText = mutableStateOf("")
    val fullNameText: State<String> = _fullNameText

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _confirmPasswordText = mutableStateOf("")
    val confirmPasswordText: State<String> = _confirmPasswordText

    private val _showConfirmPassword = mutableStateOf(false)
    val showConfirmPassword: State<Boolean> = _showConfirmPassword

    fun setUserIdText(userid: String){
        _useridText.value = userid
    }

    fun checkEmailDatabase(email: String) : Boolean{
        if (email == "shivamsharma2577@gmail.com"){
            return true
        } else {
            return false
        }                                    // need to be changed as per requirement
    }

    fun checkUserIDDatabase(userid: String) : Boolean{
        if (userid == "shivam2577"){
            return true
        } else {
            return false
        }                                    // need to be changed as per requirement
    }

    fun checkPasswordMatch(password: String, confirmPassword: String) : Boolean{
        if (password == confirmPassword){
            return false
        } else {
            return true
        }                                    // need to be changed as per requirement
    }

    fun isValidPassword(password: String): Boolean {
        // Regex pattern for the conditions
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+=-]).{8,}$".toRegex()
        return passwordRegex.matches(password)
    }

    fun setEmailText(email: String){
        _emailText.value = email
    }

    fun setFullNameText(fullName: String){
        _fullNameText.value = fullName
    }

    fun setPasswordText(password: String){
        _passwordText.value = password
    }

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    fun setConfirmPasswordText(password: String){
        _confirmPasswordText.value = password
    }

    fun setShowConfirmPassword(showPassword: Boolean) {
        _showConfirmPassword.value = showPassword
    }

}