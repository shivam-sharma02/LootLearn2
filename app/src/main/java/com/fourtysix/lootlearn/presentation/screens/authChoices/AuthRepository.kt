package com.fourtysix.lootlearn.presentation.screens.authChoices

import com.fourtysix.lootlearn.model.FbLoginModel
import com.fourtysix.lootlearn.model.ForgotPasswordModel
import com.fourtysix.lootlearn.model.LoginModel
import com.fourtysix.lootlearn.model.SignupModel
import com.fourtysix.lootlearn.model.UpdatePasswordModel
import com.fourtysix.lootlearn.model.VerifyOTPModel
import com.fourtysix.lootlearn.requestModel.FbLoginRequestModel
import com.fourtysix.lootlearn.requestModel.ForgotPasswordRequestModel
import com.fourtysix.lootlearn.requestModel.LoginRequestModel
import com.fourtysix.lootlearn.requestModel.SignupRequestModel
import com.fourtysix.lootlearn.requestModel.UpdatePasswordRequestModel
import com.fourtysix.lootlearn.requestModel.VerifyOTPRequestModel
import com.fourtysix.lootlearn.webservices.ApiClient
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