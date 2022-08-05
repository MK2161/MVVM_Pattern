package com.example.mvvmdemo.mapper

import android.util.Log
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.handler.ServiceException
import com.example.mvvmdemo.model.NotificationResponse
import com.google.gson.Gson
import retrofit2.Response

class NotificationMapper {
    companion object{
        fun map(responseModel:Response<NotificationResponse?>) : CustomResponse<NotificationResponse?,ServiceException>{
            val response = responseModel.body()
            val error = responseModel.errorBody()
            val errorResponse = Gson().fromJson(error?.string(), ServiceException::class.java)
            return if (responseModel.isSuccessful && responseModel.code() == 200) {
                if (response?.status == true) {
                    Log.d("Notification","my name is$response?.status == true")
                    CustomResponse.Success(
                        NotificationResponse(
                            statusCode = response.statusCode,
                            status = response.status,
                            message = response.message,
                            totalDoc = response.totalDoc,
                            totalPages = response.totalPages,
                            currentPage = response.currentPage,
                            data = response.data
                        )
                    )
                }else {
                    CustomResponse.Success(
                        NotificationResponse(
                            statusCode = response?.statusCode,
                            status = response?.status,
                            message = response?.message,
                            totalDoc = response?.totalDoc,
                            totalPages = response?.totalPages,
                            currentPage = response?.currentPage,
                            data = arrayListOf()
                        )
                    )
                }

            }else CustomResponse.Failure(
                ServiceException(
                    errorResponse.code,
                    errorResponse?.message,
                )
            )
        }
    }

}






