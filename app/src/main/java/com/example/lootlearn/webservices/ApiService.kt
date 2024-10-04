package com.example.lootlearn.webservices

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
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
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