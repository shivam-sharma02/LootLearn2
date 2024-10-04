package com.example.lootlearn.presentation.screens.forgotpassword

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
import com.example.lootlearn.model.ForgotPasswordModel
import com.example.lootlearn.model.LoginModel
import com.example.lootlearn.presentation.screens.authChoices.AuthRepository
import com.example.lootlearn.requestModel.ForgotPasswordRequestModel
import com.example.lootlearn.requestModel.LoginRequestModel
import com.example.lootlearn.utils.Consts
import com.example.lootlearn.utils.Screen
import com.example.lootlearn.utils.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel(){

    private val _emailText = mutableStateOf("")
    val emailText : State<String> = _emailText

    private val _isEmailEmpty = mutableStateOf(false)
    val isEmailEmpty: State<Boolean> = _isEmailEmpty

    private val _forgotPasswordResponse = MutableLiveData<ForgotPasswordModel>()
    val forgotPasswordResponse: LiveData<ForgotPasswordModel> = _forgotPasswordResponse

    private val _forgotPasswordLoading = MutableLiveData<Boolean>()
    val forgotPasswordLoading: LiveData<Boolean> = _forgotPasswordLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun setEmailText(email: String){
        _emailText.value = email
    }

    fun checkEmptyFields() {
        if (emailText.value.isEmpty()) {
            _isEmailEmpty.value = true
        } else {
            _isEmailEmpty.value = false
        }
    }

    @SuppressLint("LogNotTimber")
    fun forgotPassword(requestBody: ForgotPasswordRequestModel, navController: NavController, context: Context) {
        val sharedPreferenceHelper = SharedPreferenceHelper(context)
        _forgotPasswordLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.forgotPassword(requestBody)
                if (response.isSuccessful) {
                    _forgotPasswordLoading.postValue(false)
                    _forgotPasswordResponse.postValue(response.body())
                    Log.e("FORGOT_PASSWORD_RESPONSE", response.body().toString())

                    if (response.body() != null) {
                        if (response.body()!!.code == 200) {
                            Toast.makeText(context, response.body()!!.message!!, Toast.LENGTH_LONG).show()
                            sharedPreferenceHelper.saveString(Consts.EMAIL, emailText.value)
                            sharedPreferenceHelper.saveString(Consts.OTP_VERIFY_FROM, "forgotPassword")
                            delay(1000)
                            navController.navigate(Screen.OtpVerificationScreen.route)
                        }
                    }
                } else {
                    _forgotPasswordLoading.postValue(false)
                    val obj = JSONObject(response.errorBody()!!.string())
                    Log.e("ERROR", obj.getString("message"))
                    _errorMessage.postValue("Error: ${obj.getString("message")}")
                    Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                _forgotPasswordLoading.postValue(false)
                _errorMessage.postValue("Network Error: ${e.message}")
                Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }
    }

}