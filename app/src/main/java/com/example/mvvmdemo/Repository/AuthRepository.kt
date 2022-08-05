package com.example.mvvmdemo.Repository

import com.example.mvvmdemo.constants.USER_TYPE
import com.example.mvvmdemo.data.Api
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.handler.ServiceException
import com.example.mvvmdemo.mapper.*
import com.example.mvvmdemo.model.*
import com.example.mvvmdemo.preferenceManager.PreferenceManager
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class AuthRepository(
    private val api: Api,
    private val preferenceManager: PreferenceManager,
) {
    suspend fun doLogin(credentialModel: CredentialModel): CustomResponse<LoginResponse, ServiceException> {
        val response: Response<LoginResponse?> = api.login(credentialModel)
        return LoginMapper.map(response, preferenceManager)
    }

    suspend fun doVerify(verifyOtpRequest: VerifyOtpRequest): CustomResponse<VerifyOtpModel, ServiceException> {
        val responseModel: Response<VerifyOtpModel> = api.verifyOtp(verifyOtpRequest)
        return VerifyMapper.mapVerify(responseModel)

    }

    suspend fun requestOtp(
        email: String,
        verificationType: String
    ): CustomResponse<RequestOtpResponse?, ServiceException> {
        val rbEmail: RequestBody = email.toRequestBody(MultipartBody.FORM)
        val rbVerificationType: RequestBody = verificationType.toRequestBody(MultipartBody.FORM)
        val rbUserType: RequestBody = USER_TYPE.toRequestBody(MultipartBody.FORM)
        val response: Response<RequestOtpResponse?> = api.requestOtp(
            email = rbEmail,
            userType = rbUserType,
            mode = rbVerificationType
        )
        return ChooseOptionMapper.map(response)
    }

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): CustomResponse<ChangePasswordModel, ServiceException> {
        val response: Response<ChangePasswordModel?> = api.changePassword(changePasswordRequest)
        return ChangePasswordMapper.map(response)
    }



}




