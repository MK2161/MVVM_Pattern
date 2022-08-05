package com.example.mvvmdemo.ui.chooseOption

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.Repository.AuthRepository
import com.example.mvvmdemo.handler.CustomResponse
import kotlinx.coroutines.launch

class ChooseOptionViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val navigationLd = MutableLiveData<String>()
    private val errorMessageLd = MutableLiveData<String>()

    val error: LiveData<String> = errorMessageLd
    val navigation: LiveData<String> = navigationLd

    fun onRequestOtp(email: String,verificationType:String ) {
        viewModelScope.launch {
            when (val response = authRepository.requestOtp(email, verificationType)) {
                is CustomResponse.Success -> navigationLd.value = response.data?.data?.id.toString()
                is CustomResponse.Failure -> errorMessageLd.value =
                    response.error.message.toString()

            }
        }
    }



}







