package com.example.mvvmdemo.model

import com.google.gson.annotations.SerializedName

data class VerifyOtpModel(
    @SerializedName("status_code")
    val statusCode: Int?,

    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: String,

    @SerializedName("err")
    var error: ApiErrorModel?,
)
data class ApiErrorModel(
    @SerializedName("")
    val error: String,
)

data class VerifyOtpRequest(
    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("otp")
    var otp: String? = null,
)