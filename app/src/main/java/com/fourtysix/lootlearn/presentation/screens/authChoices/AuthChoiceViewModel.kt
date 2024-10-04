package com.fourtysix.lootlearn.presentation.screens.authChoices

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.fourtysix.lootlearn.model.FbLoginModel
import com.fourtysix.lootlearn.requestModel.FbLoginRequestModel
import com.fourtysix.lootlearn.utils.Consts
import com.fourtysix.lootlearn.utils.Screen
import com.fourtysix.lootlearn.utils.SharedPreferenceHelper
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AuthChoiceViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _fbLoginResponse = MutableLiveData<FbLoginModel>()
    val fbLoginResponse: LiveData<FbLoginModel> = _fbLoginResponse

    private val _fbLoginLoading = MutableLiveData<Boolean>()
    val fbLoginLoading: LiveData<Boolean> = _fbLoginLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun startFacebookLogin(context: android.content.Context, callbackManager: CallbackManager, navController: NavController) {
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
                    Log.e("FacebookLogin", "Login Success: ${result.accessToken.token}")

                    val token = result.accessToken.token
//                    val jwt = JWT(token)
//
//                    val name = jwt.getClaim("name").asString()
//                    val email = jwt.getClaim("email").asString()
                    _fbLoginLoading.value = true
                    val requestBody = FbLoginRequestModel(token = token)
                    fbLogin(requestBody = requestBody, context = context, navController = navController)
//                    Log.e("FacebookLogin", "Name & Email: $name === $email")
                }
            }
        )
    }

    @SuppressLint("LogNotTimber")
    fun fbLogin(requestBody: FbLoginRequestModel, context: Context, navController: NavController) {
        val sharedPreferenceHelper = SharedPreferenceHelper(context)
        viewModelScope.launch {
            try {
                val response = repository.fbLogin(requestBody)
                if (response.isSuccessful) {
                    _fbLoginLoading.value = false
                    _fbLoginResponse.postValue(response.body())
                    Log.e("FB_LOGIN_RESPONSE", response.body().toString())

                    sharedPreferenceHelper.saveString(Consts.TOKEN, response.body()!!.data!!.token!!)
                    sharedPreferenceHelper.saveString(Consts.ID, response.body()!!.data!!.user!!.Id!!)
                    sharedPreferenceHelper.saveString(Consts.NAME, response.body()!!.data!!.user!!.name!!)
                    sharedPreferenceHelper.saveString(Consts.EMAIL, response.body()!!.data!!.user!!.email!!)
                    if (response.body()!!.data!!.user!!.userId != null) {
                        sharedPreferenceHelper.saveString(Consts.USERID, response.body()!!.data!!.user!!.userId!!)
                    }
                    if (response.body()!!.data!!.user!!.password != null) {
                        sharedPreferenceHelper.saveString(Consts.PASSWORD, response.body()!!.data!!.user!!.password!!)
                    }

                    if (response.body()!!.data!!.user!!.googleId != null) {
                        sharedPreferenceHelper.saveString(Consts.GOOGLE_ID, response.body()!!.data!!.user!!.googleId!!)
                    }

                    if (response.body()!!.data!!.user!!.facebookId != null) {
                        sharedPreferenceHelper.saveString(Consts.FACEBOOK_ID, response.body()!!.data!!.user!!.facebookId!!)
                    }

                    if (response.body()!!.data!!.user!!.image != null) {
                        sharedPreferenceHelper.saveString(Consts.IMAGE, response.body()!!.data!!.user!!.image!!)
                    }
                    sharedPreferenceHelper.saveBool(Consts.IS_VERIFIED, response.body()!!.data!!.user!!.isVerified!!)
                    sharedPreferenceHelper.saveString(Consts.SIGNUP_USING, response.body()!!.data!!.user!!.signupUsing!!)
                    sharedPreferenceHelper.saveString(Consts.ROLE, response.body()!!.data!!.user!!.role!!)

                    navController.navigate(Screen.MainFeedScreen.route) {
                        popUpTo(0) { inclusive = true }
                    }
                } else {
                    _fbLoginLoading.value = false
                    val obj = JSONObject(response.errorBody()!!.string())
                    Log.e("ERROR", obj.getString("message"))
                    _errorMessage.postValue("Error: ${obj.getString("message")}")
                    Toast.makeText(context, obj.getString("message"), Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                _fbLoginLoading.value = false
                _errorMessage.postValue("Network Error: ${e.message}")
                Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_LONG).show()
            }
        }
    }
}