package com.example.lootlearn.presentation.screens.authChoices

import com.example.lootlearn.model.FbLoginModel
import com.example.lootlearn.requestModel.FbLoginRequestModel
import com.example.lootlearn.webservices.ApiClient
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(){
    suspend fun fbLogin(requestModel: FbLoginRequestModel): Response<FbLoginModel> {
        return ApiClient.api.fbLogin(requestModel)
    }
}