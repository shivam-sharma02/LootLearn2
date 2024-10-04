package com.fourtysix.lootlearn.model

import com.google.gson.annotations.SerializedName


data class LoginModel(
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("code") var code: Int? = null,
    @SerializedName("data") var data: LoginData? = LoginData(),
    @SerializedName("message") var message: String? = null,
//    @SerializedName("messages") var messages: Messages? = Messages()
)

data class LoginData(
    @SerializedName("token") var token: String? = null,
    @SerializedName("refresh_token") var refreshToken: String? = null,
    @SerializedName("user") var user: LoginUser? = LoginUser()
)

data class LoginUser(
    @SerializedName("id") var Id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("user_id") var userId: String? = null
)