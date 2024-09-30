package com.example.lootlearn.model

import com.google.gson.annotations.SerializedName


data class FbLoginModel(

    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("code") var code: Int? = null,
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("message") var message: String? = null,
//    @SerializedName("messages") var messages: Messages? = Messages()

)

data class Data(

    @SerializedName("token") var token: String? = null,
    @SerializedName("refresh_token") var refreshToken: String? = null,
    @SerializedName("user") var user: User? = User()

)

data class User(

    @SerializedName("_id") var Id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("user_id") var userId: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("google_id") var googleId: String? = null,
    @SerializedName("facebook_id") var facebookId: String? = null,
    @SerializedName("apple_id") var appleId: String? = null,
    @SerializedName("otp") var otp: String? = null,
    @SerializedName("online_status") var onlineStatus: Boolean? = null,
    @SerializedName("last_seen") var lastSeen: String? = null,
    @SerializedName("is_verified") var isVerified: Boolean? = null,
    @SerializedName("otp_expiry") var otpExpiry: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("signup_using") var signupUsing: String? = null,
    @SerializedName("role") var role: String? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("__v") var _v: Int? = null

)