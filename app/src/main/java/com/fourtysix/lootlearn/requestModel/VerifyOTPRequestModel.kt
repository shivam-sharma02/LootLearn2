package com.fourtysix.lootlearn.requestModel

data class VerifyOTPRequestModel(
    val email: String,
    val otp: String
)
