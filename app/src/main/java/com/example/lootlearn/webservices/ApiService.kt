package com.example.lootlearn.webservices

import com.example.lootlearn.model.FbLoginModel
import com.example.lootlearn.requestModel.FbLoginRequestModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/facebook-login")
    suspend fun fbLogin(@Body requestBody: FbLoginRequestModel): Response<FbLoginModel>
}