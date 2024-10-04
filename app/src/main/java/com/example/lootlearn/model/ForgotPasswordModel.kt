package com.example.lootlearn.model

import com.google.gson.annotations.SerializedName


data class ForgotPasswordModel(
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("code") var code: Int? = null,
//    @SerializedName("data") var data: VerifyOTPData? = VerifyOTPData(),
    @SerializedName("message") var message: String? = null,
//    @SerializedName("messages") var messages: Messages? = Messages()
)