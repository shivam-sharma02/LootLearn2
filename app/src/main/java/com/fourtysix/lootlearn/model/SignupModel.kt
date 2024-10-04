package com.fourtysix.lootlearn.model

import com.google.gson.annotations.SerializedName


data class SignupModel(
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("code") var code: Int? = null,
    @SerializedName("data") var data: SignupData? = SignupData(),
    @SerializedName("message") var message: String? = null,
//    @SerializedName("messages") var messages: Messages? = Messages()
)

data class SignupData(
    @SerializedName("user") var user: SignupUser? = SignupUser()
)

data class SignupUser(
    @SerializedName("id") var Id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null
)