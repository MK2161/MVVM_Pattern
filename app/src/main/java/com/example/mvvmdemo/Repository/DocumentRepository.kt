package com.example.mvvmdemo.Repository

import com.example.mvvmdemo.data.Api
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.handler.ServiceException
import com.example.mvvmdemo.mapper.DocumentMapper
import com.example.mvvmdemo.model.DocumentListResponse
import com.example.mvvmdemo.preferenceManager.PreferenceManager
import retrofit2.Response

class DocumentRepository(
    private val api : Api,
    private val preferenceManager: PreferenceManager,) {

    suspend fun getDocumentList(isStarred:Boolean? = null):CustomResponse<DocumentListResponse, ServiceException>{
        val response: Response<DocumentListResponse?> = api.getRecentDocumentList(
            userId = preferenceManager.getStaffId(),
            limit = 20,
            pageNumber = 1,
            isStarred = isStarred
        )
        return DocumentMapper.map(response)
    }

}


