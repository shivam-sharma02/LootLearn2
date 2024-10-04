package com.example.lootlearn.presentation.screens.authChoices

import com.example.lootlearn.model.FbLoginModel
import com.example.lootlearn.model.ForgotPasswordModel
import com.example.lootlearn.model.LoginModel
import com.example.lootlearn.model.SignupModel
import com.example.lootlearn.model.UpdatePasswordModel
import com.example.lootlearn.model.VerifyOTPModel
import com.example.lootlearn.requestModel.FbLoginRequestModel
import com.example.lootlearn.requestModel.ForgotPasswordRequestModel
import com.example.lootlearn.requestModel.LoginRequestModel
import com.example.lootlearn.requestModel.SignupRequestModel
import com.example.lootlearn.requestModel.UpdatePasswordRequestModel
import com.example.lootlearn.requestModel.VerifyOTPRequestModel
import com.example.lootlearn.webservices.ApiClient
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(){
    suspend fun fbLogin(requestModel: FbLoginRequestModel): Response<FbLoginModel> {
        return ApiClient.api.fbLogin(requestModel)
    }

    suspend fun signup(requestModel: SignupRequestModel): Response<SignupModel> {
        return ApiClient.api.signup(requestModel)
    }

    suspend fun login(requestModel: LoginRequestModel): Response<LoginModel> {
        return ApiClient.api.login(requestModel)
    }

    suspend fun verifyOTP(requestModel: VerifyOTPRequestModel): Response<VerifyOTPModel> {
        return ApiClient.api.verifyOTP(requestModel)
    }

    suspend fun forgotPassword(requestModel: ForgotPasswordRequestModel): Response<ForgotPasswordModel> {
        return ApiClient.api.forgotPassword(requestModel)
    }

    suspend fun updatePassword(token: String, requestModel: UpdatePasswordRequestModel): Response<UpdatePasswordModel> {
        return ApiClient.api.updatePassword(token, requestModel)
    }
}