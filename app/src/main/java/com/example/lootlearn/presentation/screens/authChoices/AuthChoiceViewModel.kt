package com.example.lootlearn.presentation.screens.authChoices

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.lootlearn.model.FbLoginModel
import com.example.lootlearn.requestModel.FbLoginRequestModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AuthChoiceViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {

    private val _fbLoginResponse = MutableLiveData<FbLoginModel>()
    val fbLoginResponse: LiveData<FbLoginModel> = _fbLoginResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun startFacebookLogin(context: android.content.Context, callbackManager: CallbackManager) {
        LoginManager.getInstance().logInWithReadPermissions(
            context as ComponentActivity,
            callbackManager,
            listOf("public_profile", "email")
        )

        // Registering the callback with all required methods
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                @SuppressLint("LogNotTimber")
                override fun onCancel() {
                    // Login canceled
                    Log.e("FacebookLogin", "Login Canceled")
                }

                @SuppressLint("LogNotTimber")
                override fun onError(error: FacebookException) {
                    Log.e("FacebookLogin", "Login Error: ${error.message}")
                }

                @SuppressLint("LogNotTimber")
                override fun onSuccess(result: LoginResult) {
                    Log.e("FacebookLogin", "Login Success: ${result.authenticationToken?.token}")

                    val token = result.authenticationToken?.token!!
                    val jwt = JWT(token)

                    val name = jwt.getClaim("name").asString()
                    val email = jwt.getClaim("email").asString()

                    val requestBody = FbLoginRequestModel(token = token)
                    fbLogin(requestBody = requestBody)

                    Log.e("FacebookLogin", "Name & Email: $name === $email")
                }
            }
        )
    }

    fun fbLogin(requestBody: FbLoginRequestModel) {

        viewModelScope.launch {
            try {
                val response = repository.fbLogin(requestBody)
                if (response.isSuccessful) {
                    _fbLoginResponse.postValue(response.body())
                } else {
                    _errorMessage.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Network Error: ${e.message}")
            }
        }
    }
}