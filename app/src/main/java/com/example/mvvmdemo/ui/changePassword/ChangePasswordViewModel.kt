package com.example.mvvmdemo.ui.changePassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.Repository.AuthRepository
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.model.ChangePasswordRequest
import kotlinx.coroutines.launch

class ChangePasswordViewModel(private val authRepository: AuthRepository):ViewModel() {



    private val navigationLd = MutableLiveData<String>()
    private val errorMessageLd = MutableLiveData<String>()

    val error: LiveData<String> = errorMessageLd
    val navigation: LiveData<String> = navigationLd


    fun onVerify(password:String,confirmPassword:String,verificationId:String){
        if (password.isEmpty()) {
            errorMessageLd.value = "provide valid credential"
        }else if (confirmPassword.isBlank()) {
            errorMessageLd.value = "provide valid credential"
        }else if (password != confirmPassword){
            errorMessageLd.value = "provide valid credential"
        }else{
            viewModelScope.launch {
                when (val response = authRepository.changePassword(getChangePasswordRequest(password,verificationId))) {
                    is CustomResponse.Success -> navigationLd.value = response.data.data?.verificationId.toString()
                    is CustomResponse.Failure -> errorMessageLd.value =
                        response.error.message.toString()
                }
            }
        }

    }

    private fun getChangePasswordRequest(password:String,verificationId: String):ChangePasswordRequest{
        return ChangePasswordRequest(
            verificationId = verificationId,
            password = password,

        )
    }



}