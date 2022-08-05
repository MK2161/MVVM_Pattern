package com.example.mvvmdemo.mapper

import com.example.mvvmdemo.constants.USER_TYPE
import com.example.mvvmdemo.data.Api
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.handler.ServiceException
import com.example.mvvmdemo.model.LoginResponse
import com.example.mvvmdemo.model.RequestOtpResponse
import com.example.mvvmdemo.model.VerifyOtpModel
import com.example.mvvmdemo.preferenceManager.PreferenceManager
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class ChooseOptionMapper {
    companion object {
        fun map(responseModel: Response<RequestOtpResponse?>): CustomResponse<RequestOtpResponse, ServiceException> {
            val response = responseModel.body()
            val error = responseModel.errorBody()
            val errorResponse = Gson().fromJson(error?.string(), ServiceException::class.java)
            return if (responseModel.isSuccessful && responseModel.code() == 200) {
                if (response?.status == true) {
                    CustomResponse.Success(
                        RequestOtpResponse(
                            statusCode = response.statusCode,
                            status = response.status,
                            message = response.message,
                            data = response.data
                        )

                    )
                } else CustomResponse.Failure(
                    ServiceException(
                        errorResponse.code,
                        response?.message,
                    )
                )
            } else CustomResponse.Failure(
                ServiceException(
                    errorResponse.code,
                    errorResponse.message,
                )
            )
        }
    }
}



