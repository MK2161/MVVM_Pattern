package com.example.mvvmdemo.mapper

import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.handler.ServiceException
import com.example.mvvmdemo.model.DocumentListResponse
import com.google.gson.Gson
import retrofit2.Response

class DocumentMapper {
    companion object {
        fun map(responseModel: Response<DocumentListResponse?>): CustomResponse<DocumentListResponse, ServiceException> {
            val response = responseModel.body()
            val error = responseModel.errorBody()
            val errorResponse = Gson().fromJson(error?.string(), ServiceException::class.java)
            return if (responseModel.isSuccessful && responseModel.code() == 200) {
                if (response?.status == true) {
                    CustomResponse.Success(
                        DocumentListResponse(
                            statusCode = response.statusCode,
                            status = response.status,
                            message = response.message,
                            totalDoc = response.totalDoc,
                            totalPages = response.totalPages,
                            currentPage = response.currentPage,
                            documentList = response.documentList
                        )
                    )
                } else {
                    CustomResponse.Success(
                        DocumentListResponse(
                            statusCode = response?.statusCode,
                            status = response?.status,
                            message = response?.message,
                            totalDoc = response?.totalDoc,
                            totalPages = response?.totalPages,
                            currentPage = response?.currentPage,
                            documentList = arrayListOf()
                        )
                    )
                }

            } else CustomResponse.Failure(
                ServiceException(
                    errorResponse.code,
                    errorResponse?.message,
                )
            )
        }

    }

}



