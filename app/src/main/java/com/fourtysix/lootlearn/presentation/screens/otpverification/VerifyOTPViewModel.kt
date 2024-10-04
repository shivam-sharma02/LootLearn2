package com.fourtysix.lootlearn.presentation.screens.otpverification

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
import com.fourtysix.lootlearn.model.VerifyOTPModel
import com.fourtysix.lootlearn.presentation.screens.authChoices.AuthRepository
import com.fourtysix.lootlearn.requestModel.VerifyOTPRequestModel
import com.fourtysix.lootlearn.utils.Consts
import com.fourtysix.lootlearn.utils.Screen
import com.fourtysix.lootlearn.utils.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
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

    fun checkEmptyFields(otp: String) {
        _isEmpty.value = otp.isEmpty() || otp.length < 4
    }

    @SuppressLint("LogNotTimber")
    fun verifyOTP(requestBody: VerifyOTPRequestModel, navController: NavController, context: Context) {
        val sharedPreferenceHelper = SharedPreferenceHelper(context)

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

                            sharedPreferenceHelper.saveString(Consts.TOKEN, response.body()!!.data!!.token!!)
                            sharedPreferenceHelper.saveString(Consts.ID, response.body()!!.data!!.user!!.Id!!)
                            sharedPreferenceHelper.saveString(Consts.NAME, response.body()!!.data!!.user!!.name!!)
                            sharedPreferenceHelper.saveString(Consts.EMAIL, response.body()!!.data!!.user!!.email!!)
                            sharedPreferenceHelper.saveString(Consts.USERID, response.body()!!.data!!.user!!.userId!!)
                            sharedPreferenceHelper.saveString(Consts.PASSWORD, response.body()!!.data!!.user!!.password!!)
                            if (response.body()!!.data!!.user!!.google_id != null) {
                                sharedPreferenceHelper.saveString(Consts.GOOGLE_ID, response.body()!!.data!!.user!!.google_id!!)
                            }

                            if (response.body()!!.data!!.user!!.facebook_id != null) {
                                sharedPreferenceHelper.saveString(Consts.FACEBOOK_ID, response.body()!!.data!!.user!!.facebook_id!!)
                            }

                            if (response.body()!!.data!!.user!!.image != null) {
                                sharedPreferenceHelper.saveString(Consts.IMAGE, response.body()!!.data!!.user!!.image!!)
                            }
                            sharedPreferenceHelper.saveBool(Consts.IS_VERIFIED, response.body()!!.data!!.user!!.is_verified!!)
                            sharedPreferenceHelper.saveString(Consts.SIGNUP_USING, response.body()!!.data!!.user!!.signup_using!!)
                            sharedPreferenceHelper.saveString(Consts.ROLE, response.body()!!.data!!.user!!.role!!)
                            sharedPreferenceHelper.saveBool(Consts.IS_ACTIVE, response.body()!!.data!!.user!!.is_active!!)

                            delay(1000)

                            if (sharedPreferenceHelper.getString(Consts.OTP_VERIFY_FROM) == "signup") {
                                navController.navigate(Screen.MainFeedScreen.route) {
                                    popUpTo(0) { inclusive = true }
                                }
                            } else {
                                navController.navigate(Screen.NewPasswordScreen.route)
                            }
                        }
                    }
                } else {
                    _verifyOTPLoading.postValue(false)
                    val obj = JSONObject(response.errorBody()!!.string())
                    Log.e("ERROR", obj.getString("message"))
                    _errorMessage.postValue("Error: ${obj.getString("message")}")
                    Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                _verifyOTPLoading.postValue(false)
                _errorMessage.postValue("Network Error: ${e.message}")
                Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }
    }
}