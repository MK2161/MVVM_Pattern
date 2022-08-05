package com.example.mvvmdemo.model

import com.google.gson.annotations.SerializedName

data class RequestOtpResponse(
    @SerializedName("status_code")
    val statusCode: Int?,

    @SerializedName("status")
    val status: Boolean?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: RequestOtpDataModel?,
)
data class RequestOtpDataModel(
    @SerializedName("_id")
    val id: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("mobile")
    val mobile: String?,
)
data class RequestOtpDto(
    val message: String?,
    val email: String?,
    val mobile: String?,
    val status: Boolean?,
    val verificationId: String?,
)