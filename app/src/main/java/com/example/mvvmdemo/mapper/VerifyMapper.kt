package com.example.mvvmdemo.mapper

import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.handler.ServiceException
import com.example.mvvmdemo.model.VerifyOtpModel
import com.google.gson.Gson
import retrofit2.Response

class VerifyMapper {
    companion object {
        fun mapVerify(
            responseType: Response<VerifyOtpModel>,
        ): CustomResponse<VerifyOtpModel, ServiceException> {
            val response = responseType.body()
            val error = responseType.errorBody()
            val errorResponse = Gson().fromJson(error?.string(), ServiceException::class.java)
            return if (responseType.isSuccessful && responseType.code() == 200) {
                if (response?.status == true) {
                    CustomResponse.Success(
                        VerifyOtpModel(
                            statusCode = response.statusCode,
                            status = response.status,
                            message = response.message,
                            data = response.data,
                            error = response.error
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