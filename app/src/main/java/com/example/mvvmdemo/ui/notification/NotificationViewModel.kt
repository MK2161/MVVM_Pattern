package com.example.mvvmdemo.ui.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.Repository.AuthRepository
import com.example.mvvmdemo.Repository.NotificationRepository
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.model.NotificationItem
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    private val notificationLd = MutableLiveData<ArrayList<NotificationItem>>()
    private val errorMessageLd = MutableLiveData<String>()

    val error: LiveData<String> = errorMessageLd
    val notification: LiveData<ArrayList<NotificationItem>> = notificationLd

    init {
        viewModelScope.launch {
            when (val response = notificationRepository.onNotification()) {
                is CustomResponse.Success -> notificationLd.value = response.data?.data
                is CustomResponse.Failure -> errorMessageLd.value =
                    response.error.message.toString()

            }
        }
    }
}