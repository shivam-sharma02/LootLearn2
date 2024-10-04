package com.fourtysix.lootlearn.webservices

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
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("auth/facebook-login")
    suspend fun fbLogin(@Body requestBody: FbLoginRequestModel): Response<FbLoginModel>

    @POST("auth/register")
    suspend fun signup(@Body requestBody: SignupRequestModel): Response<SignupModel>

    @POST("auth/login")
    suspend fun login(@Body requestBody: LoginRequestModel): Response<LoginModel>

    @POST("auth/verify-otp")
    suspend fun verifyOTP(@Body requestBody: VerifyOTPRequestModel): Response<VerifyOTPModel>

    @POST("auth/forgot-password")
    suspend fun forgotPassword(@Body requestBody: ForgotPasswordRequestModel): Response<ForgotPasswordModel>

    @POST("auth/update-password")
    suspend fun updatePassword(@Header("Authorization") token: String, @Body requestBody: UpdatePasswordRequestModel): Response<UpdatePasswordModel>
}