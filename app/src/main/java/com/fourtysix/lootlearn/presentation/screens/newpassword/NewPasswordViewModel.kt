package com.fourtysix.lootlearn.presentation.screens.newpassword

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
import com.fourtysix.lootlearn.model.UpdatePasswordModel
import com.fourtysix.lootlearn.presentation.screens.authChoices.AuthRepository
import com.fourtysix.lootlearn.requestModel.UpdatePasswordRequestModel
import com.fourtysix.lootlearn.utils.Consts
import com.fourtysix.lootlearn.utils.Screen
import com.fourtysix.lootlearn.utils.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {

    private val _newPasswordText = mutableStateOf("")
    val newPasswordText: State<String> = _newPasswordText
    private val _isNewPassEmpty = mutableStateOf(false)
    val isNewPassEmpty: State<Boolean> = _isNewPassEmpty

    private val _showNewPassword = mutableStateOf(false)
    val showNewPassword: State<Boolean> = _showNewPassword

    private val _confirmPasswordText = mutableStateOf("")
    val confirmPasswordText: State<String> = _confirmPasswordText
    private val _isConfirmPassEmpty = mutableStateOf(false)
    val isConfirmPassEmpty: State<Boolean> = _isConfirmPassEmpty

    private val _showConfirmPassword = mutableStateOf(false)
    val showConfirmPassword: State<Boolean> = _showConfirmPassword

    private val _updatePasswordResponse = MutableLiveData<UpdatePasswordModel>()
    val updatePasswordResponse: LiveData<UpdatePasswordModel> = _updatePasswordResponse

    private val _updatePasswordLoading = MutableLiveData<Boolean>()
    val updatePasswordLoading: LiveData<Boolean> = _updatePasswordLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

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

    fun checkPasswordMatch(password: String, confirmPassword: String) : Boolean{
        if (password == confirmPassword){
            return false
        } else {
            return true
        }
    }

    fun isValidPassword(password: String): Boolean {
        // Regex pattern for the conditions
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+=-]).{8,}$".toRegex()
        return passwordRegex.matches(password)
    }

    fun checkEmptyFields() {
        _isNewPassEmpty.value = newPasswordText.value.isEmpty()
        _isConfirmPassEmpty.value = confirmPasswordText.value.isEmpty()
    }

    @SuppressLint("LogNotTimber")
    fun updatePassword(requestBody: UpdatePasswordRequestModel, navController: NavController, context: Context) {
        val sharedPreferenceHelper = SharedPreferenceHelper(context)
        _updatePasswordLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.updatePassword("Bearer " + sharedPreferenceHelper.getString(Consts.TOKEN), requestBody)
                if (response.isSuccessful) {
                    _updatePasswordLoading.postValue(false)
                    _updatePasswordResponse.postValue(response.body())
                    Log.e("UPDATE_PASSWORD_RESPONSE", response.body().toString())

                    if (response.body() != null) {
                        if (response.body()!!.code == 200) {
                            Toast.makeText(context, response.body()!!.message!!, Toast.LENGTH_LONG).show()
                            delay(1000)
                            navController.navigate(Screen.CongratulationScreen.route)
                        }
                    }
                } else {
                    _updatePasswordLoading.postValue(false)
                    val obj = JSONObject(response.errorBody()!!.string())
                    Log.e("ERROR", obj.getString("message"))
                    _errorMessage.postValue("Error: ${obj.getString("message")}")
                    Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                _updatePasswordLoading.postValue(false)
                _errorMessage.postValue("Network Error: ${e.message}")
                Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }
    }

}