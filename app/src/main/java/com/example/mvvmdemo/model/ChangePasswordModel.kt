package com.example.mvvmdemo.model

import com.google.gson.annotations.SerializedName

data class ChangePasswordModel(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ChangePasswordDataModel?,
    @SerializedName("error")
    val error: ApiErrorModel?,
)

data class ChangePasswordDataModel(
    @SerializedName("_id")
    val verificationId: String,
    /*@SerializedName("name")
    val name: FaceNameModel,*/
    @SerializedName("email")
    val email: String?,
    @SerializedName("user_type")
    val userType: String?,
    @SerializedName("biometric_data")
    val userImage: String?,
)