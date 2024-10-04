package com.fourtysix.lootlearn.presentation.screens.login

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.fourtysix.lootlearn.model.LoginModel
import com.fourtysix.lootlearn.presentation.screens.authChoices.AuthRepository
import com.fourtysix.lootlearn.requestModel.LoginRequestModel
import com.fourtysix.lootlearn.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class LogInViewModel @Inject constructor(private val repository: AuthRepository): ViewModel(){

    private val _useridText = mutableStateOf("")
    val useridText: State<String> = _useridText
    private val _isUseridEmpty = mutableStateOf(false)
    val isUseridEmpty: State<Boolean> = _isUseridEmpty

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText
    private val _isPasswordEmpty = mutableStateOf(false)
    val isPasswordEmpty: State<Boolean> = _isPasswordEmpty

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _loginResponse = MutableLiveData<LoginModel>()
    val loginResponse: LiveData<LoginModel> = _loginResponse

    private val _loginLoading = MutableLiveData<Boolean>()
    val loginLoading: LiveData<Boolean> = _loginLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun setUserIdText(userid: String){
        _useridText.value = userid
    }

    fun setPasswordText(password: String){
        _passwordText.value = password
    }

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    fun checkEmptyFields() {
        if (useridText.value.isEmpty()) {
            _isUseridEmpty.value = true
        } else {
            _isUseridEmpty.value = false
        }

        if (passwordText.value.isEmpty()) {
            _isPasswordEmpty.value = true
        } else {
            _isPasswordEmpty.value = false
        }
    }

    @SuppressLint("LogNotTimber")
    fun login(requestBody: LoginRequestModel, navController: NavController, context: Context) {
        _loginLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.login(requestBody)
                if (response.isSuccessful) {
                    _loginLoading.postValue(false)
                    _loginResponse.postValue(response.body())
                    Log.e("SIGNUP_RESPONSE", response.body().toString())

                    if (response.body() != null) {
                        if (response.body()!!.code == 200) {
                            Toast.makeText(context, response.body()!!.message!!, Toast.LENGTH_LONG).show()
                            delay(1000)  // the delay of 3 seconds
                            navController.navigate(Screen.MainFeedScreen.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                } else {
                    _loginLoading.postValue(false)
                    try {
                        val obj = JSONObject(response.errorBody()!!.string())
                        Log.e("ERROR", obj.getString("message"))
                        _errorMessage.postValue("Error: ${obj.getString("message")}")
                        Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show()
                    } catch (t: Throwable) {
                        Log.e("ERROR", "Could not parse malformed JSON: \"${response.errorBody()!!.string()}\"")
                    }
                }
            } catch (e: Exception) {
                _loginLoading.postValue(false)
                _errorMessage.postValue("Network Error: ${e.message}")
                Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }
    }
}