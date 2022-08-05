package com.example.mvvmdemo.data
import com.example.mvvmdemo.model.*
import com.example.mvvmdemo.model.CredentialModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @POST("v1/digiclass/user/authLogin")
    suspend fun login(
        @Body credentials: CredentialModel,
    ): Response<LoginResponse?>

    @Multipart
    @POST("v1/digiclass/user/forget")
    suspend fun requestOtp(
        @Part("email") email: RequestBody,
        @Part("validation_mode") mode: RequestBody,
        @Part("user_type") userType: RequestBody,
    ): Response<RequestOtpResponse?>

    @POST("v1/digiclass/user/otp_verify")
    suspend fun verifyOtp(
        @Body verifyOtp: VerifyOtpRequest,
    ): Response<VerifyOtpModel>

    @POST("v1/digiclass/user/set_password")
    suspend fun changePassword(
        @Body changePasswordModel: ChangePasswordRequest,
    ): Response<ChangePasswordModel?>
    @GET("v1/digiclass/notification/{userId}")

    suspend fun getNotifications(
        @Path("userId") userId: String?,
        @Query("limit") limit: Int? = 10,
        @Query("pageNo") pageNumber: Int = 1,
    ): Response<NotificationResponse?>

    @GET("v1/digiclass/document/{userId}")
    suspend fun getRecentDocumentList(
        @Path("userId") userId: String?,
        @Query("limit") limit: Int?,
        @Query("pageNo") pageNumber: Int?,
        @Query("tab") isStarred: Boolean? = null,
    ): Response<DocumentListResponse?>
}