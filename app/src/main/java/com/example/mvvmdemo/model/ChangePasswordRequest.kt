package com.example.mvvmdemo.model

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("_id")
    val verificationId: String,
    @SerializedName("new_password")
    val password: String?,
)
