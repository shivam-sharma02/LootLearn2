package com.fourtysix.lootlearn.model

import com.google.gson.annotations.SerializedName


data class VerifyOTPModel(
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("code") var code: Int? = null,
    @SerializedName("data") var data: VerifyOTPData? = VerifyOTPData(),
    @SerializedName("message") var message: String? = null,
//    @SerializedName("messages") var messages: Messages? = Messages()
)

data class VerifyOTPData(
    @SerializedName("token") var token: String? = null,
    @SerializedName("refresh_token") var refreshToken: String? = null,
    @SerializedName("user") var user: VerifyOTPUser? = VerifyOTPUser()
)

data class VerifyOTPUser(
    @SerializedName("_id") var Id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("user_id") var userId: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("google_id") var google_id: String? = null,
    @SerializedName("facebook_id") var facebook_id: String? = null,
    @SerializedName("apple_id") var apple_id: String? = null,
    @SerializedName("otp") var otp: String? = null,
    @SerializedName("online_status") var online_status: String? = null,
    @SerializedName("last_seen") var last_seen: String? = null,
    @SerializedName("is_verified") var is_verified: Boolean? = null,
    @SerializedName("otp_expiry") var otp_expiry: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("signup_using") var signup_using: String? = null,
    @SerializedName("role") var role: String? = null,
    @SerializedName("is_active") var is_active: Boolean? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("__v") var __v: Int? = null
)