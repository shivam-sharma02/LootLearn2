package com.example.lootlearn.presentation.screens.otpverification

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
import com.example.lootlearn.model.LoginModel
import com.example.lootlearn.model.SignupModel
import com.example.lootlearn.model.VerifyOTPModel
import com.example.lootlearn.presentation.screens.authChoices.AuthRepository
import com.example.lootlearn.requestModel.LoginRequestModel
import com.example.lootlearn.requestModel.SignupRequestModel
import com.example.lootlearn.requestModel.VerifyOTPRequestModel
import com.example.lootlearn.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VerifyOTPViewModel @Inject constructor(private val repository: AuthRepository): ViewModel(){

    private val _firstText = mutableStateOf("")
    val firstText: State<String> = _firstText
    private val _isEmpty = mutableStateOf(false)
    val isEmpty: State<Boolean> = _isEmpty

    private val _secondText = mutableStateOf("")
    val secondText: State<String> = _secondText

    private val _thirdText = mutableStateOf("")
    val thirdText: State<String> = _thirdText

    private val _fourthText = mutableStateOf("")
    val fourthText: State<String> = _fourthText

    private val _verifyOTPResponse = MutableLiveData<VerifyOTPModel>()
    val verifyOTPResponse: LiveData<VerifyOTPModel> = _verifyOTPResponse

    private val _verifyOTPLoading = MutableLiveData<Boolean>()
    val verifyOTPLoading: LiveData<Boolean> = _verifyOTPLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun setFirstText(firstText: String){
        _firstText.value = firstText
    }

    fun setSecondText(secondText: String){
        _secondText.value = secondText
    }

    fun setThirdText(thirdText: String){
        _thirdText.value = thirdText
    }

    fun setFourthText(fourthText: String){
        _fourthText.value = fourthText
    }

    fun checkEmptyFields() {
        if (firstText.value.isEmpty() || secondText.value.isEmpty() || thirdText.value.isEmpty() || fourthText.value.isEmpty()) {
            _isEmpty.value = true
        } else {
            _isEmpty.value = false
        }
    }

    @SuppressLint("LogNotTimber")
    fun verifyOTP(requestBody: VerifyOTPRequestModel, navController: NavController, context: Context) {
        _verifyOTPLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.verifyOTP(requestBody)
                if (response.isSuccessful) {
                    _verifyOTPLoading.postValue(false)
                    _verifyOTPResponse.postValue(response.body())
                    Log.e("VERIFY_OTP_RESPONSE", response.body().toString())

                    if (response.body() != null) {
                        if (response.body()!!.code == 200) {
                            Toast.makeText(context, response.body()!!.message!!, Toast.LENGTH_LONG).show()
                            delay(2000)  // the delay of 3 seconds
                            navController.navigate(Screen.MainFeedScreen.route)
                        }
                    }
                } else {
                    _verifyOTPLoading.postValue(false)
                    _errorMessage.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _verifyOTPLoading.postValue(false)
                _errorMessage.postValue("Network Error: ${e.message}")
            }
        }
    }
}