package com.example.mvvmdemo.mapper

import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.handler.ServiceException
import com.example.mvvmdemo.model.LoginResponse
import com.example.mvvmdemo.preferenceManager.PreferenceManager
import com.google.gson.Gson
import retrofit2.Response

class LoginMapper {
    companion object {
        fun map(responseModel: Response<LoginResponse?>,preferenceManager: PreferenceManager) :CustomResponse<LoginResponse,ServiceException>{
            val response = responseModel.body()
            val error = responseModel.errorBody()
            val errorResponse = Gson().fromJson(error?.string(), ServiceException::class.java)
            return if (responseModel.isSuccessful && responseModel.code() == 200) {
                preferenceManager.setStaffId(response?.data?.staffId)
                preferenceManager.setAccessToken(response?.data?.tokens?.accessToken?.token)
                preferenceManager.setRefreshToken(response?.data?.tokens?.refreshToken?.token)
                if (response?.status == true) {
                    CustomResponse.Success(
                        LoginResponse(
                            status = response.status,
                            statusCode = response.statusCode,
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


