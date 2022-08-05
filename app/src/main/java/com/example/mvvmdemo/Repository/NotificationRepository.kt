package com.example.mvvmdemo.Repository

import com.example.mvvmdemo.data.Api
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.handler.ServiceException
import com.example.mvvmdemo.mapper.NotificationMapper
import com.example.mvvmdemo.model.NotificationResponse
import com.example.mvvmdemo.preferenceManager.PreferenceManager
import retrofit2.Response

class NotificationRepository(
    private val api : Api,
    private val preferenceManager: PreferenceManager,
) {


    suspend fun onNotification(): CustomResponse<NotificationResponse?, ServiceException> {
        val response: Response<NotificationResponse?> = api.getNotifications(
            userId = preferenceManager.getStaffId(),
            limit  = 10,
            pageNumber = 1
        )
        return NotificationMapper.map(response)
    }
}