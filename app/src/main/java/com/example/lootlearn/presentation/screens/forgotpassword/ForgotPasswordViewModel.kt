package com.example.lootlearn.presentation.screens.forgotpassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ForgotPasswordViewModel @Inject constructor() : ViewModel(){

    private val _emailText = mutableStateOf("")
    val emailText : State<String> = _emailText

    fun setEmailText(email: String){
        _emailText.value = email
    }

}