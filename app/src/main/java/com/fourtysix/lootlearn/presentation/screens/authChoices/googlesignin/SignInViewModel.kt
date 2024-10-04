package com.fourtysix.lootlearn.presentation.screens.authChoices.googlesignin

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(): ViewModel() {

    private  val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            val isSuccess = result.data != null
            val error = result.errorMessage

            Log.d("SignInViewModel", "SignIn success: ${result.data}, error: $error")
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }




    fun resetState() {
        Log.d("SignInViewModel", "Resetting sign-in state.")
        _state.update { SignInState() }
    }

}