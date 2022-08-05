package com.example.mvvmdemo.mapper

import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.handler.ServiceException
import com.example.mvvmdemo.model.ChangePasswordModel
import com.google.gson.Gson
import retrofit2.Response


class ChangePasswordMapper {
    companion object {
        fun map(responseModel: Response<ChangePasswordModel?>): CustomResponse<ChangePasswordModel, ServiceException> {
            val response = responseModel.body()
            val error = responseModel.errorBody()
            val errorResponse = Gson().fromJson(error?.string(), ServiceException::class.java)
            return if (responseModel.isSuccessful && responseModel.code() == 200) {
                if (response?.status == true) {
                    CustomResponse.Success(
                        ChangePasswordModel(
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
                        errorResponse?.message,
                    )
                )
            } else CustomResponse.Failure(
                ServiceException(
                    errorResponse.code,
                    response?.message,
                )
            )
        }
    }
}





