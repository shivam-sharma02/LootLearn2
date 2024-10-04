package com.example.lootlearn.presentation.screens.signup

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.lootlearn.model.FbLoginModel
import com.example.lootlearn.model.SignupModel
import com.example.lootlearn.presentation.screens.authChoices.AuthRepository
import com.example.lootlearn.requestModel.FbLoginRequestModel
import com.example.lootlearn.requestModel.SignupRequestModel
import com.example.lootlearn.utils.Consts
import com.example.lootlearn.utils.Screen
import com.example.lootlearn.utils.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {

    private val _useridText = mutableStateOf("")
    val useridText: State<String> = _useridText
    private val _isUseridEmpty = mutableStateOf(false)
    val isUseridEmpty: State<Boolean> = _isUseridEmpty

    private val _fullNameText = mutableStateOf("")
    val fullNameText: State<String> = _fullNameText
    private val _isFullNameEmpty = mutableStateOf(false)
    val isFullNameEmpty: State<Boolean> = _isFullNameEmpty

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText
    private val _isEmailEmpty = mutableStateOf(false)
    val isEmailEmpty: State<Boolean> = _isEmailEmpty

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText
    private val _isPasswordEmpty = mutableStateOf(false)
    val isPasswordEmpty: State<Boolean> = _isPasswordEmpty

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _confirmPasswordText = mutableStateOf("")
    val confirmPasswordText: State<String> = _confirmPasswordText
    private val _isConfirmPasswordEmpty = mutableStateOf(false)
    val isConfirmPasswordEmpty: State<Boolean> = _isConfirmPasswordEmpty

    private val _showConfirmPassword = mutableStateOf(false)
    val showConfirmPassword: State<Boolean> = _showConfirmPassword


    private val _signupResponse = MutableLiveData<SignupModel>()
    val signupResponse: LiveData<SignupModel> = _signupResponse

    private val _signupLoading = MutableLiveData<Boolean>()
    val signupLoading: LiveData<Boolean> = _signupLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage


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

    fun checkEmptyFields() {
        if (fullNameText.value.isEmpty()) {
            _isFullNameEmpty.value = true
        } else {
            _isFullNameEmpty.value = false
        }

        if (emailText.value.isEmpty()) {
            _isEmailEmpty.value = true
        } else {
            _isEmailEmpty.value = false
        }

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

        if (confirmPasswordText.value.isEmpty()) {
            _isConfirmPasswordEmpty.value = true
        } else {
            _isConfirmPasswordEmpty.value = false
        }
    }

    @SuppressLint("LogNotTimber")
    fun signup(requestBody: SignupRequestModel, navController: NavController, context: Context) {
        val sharedPreferenceHelper = SharedPreferenceHelper(context)
        _signupLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.signup(requestBody)
                if (response.isSuccessful) {
                    _signupLoading.postValue(false)
                    _signupResponse.postValue(response.body())
                    Log.e("SIGNUP_RESPONSE", response.body().toString())

                    if (response.body() != null) {
                        if (response.body()!!.code == 200) {
                            Toast.makeText(context, response.body()!!.message!!, Toast.LENGTH_LONG).show()
                            sharedPreferenceHelper.saveString(Consts.EMAIL, response.body()!!.data!!.user!!.email!!)
                            sharedPreferenceHelper.saveString(Consts.OTP_VERIFY_FROM, "signup")
                            delay(1000)
                            navController.navigate(Screen.OtpVerificationScreen.route)
                        }
                    }
                } else {
                    _signupLoading.postValue(false)
                    val obj = JSONObject(response.errorBody()!!.string())
                    Log.e("ERROR", obj.getString("message"))
                    _errorMessage.postValue("Error: ${obj.getString("message")}")
                    Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                _signupLoading.postValue(false)
                _errorMessage.postValue("Network Error: ${e.message}")
                Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }
    }

}