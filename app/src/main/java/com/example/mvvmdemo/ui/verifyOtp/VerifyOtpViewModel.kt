package com.example.mvvmdemo.ui.verifyOtp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.Repository.AuthRepository
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.model.VerifyOtpRequest
import kotlinx.android.synthetic.main.activity_verify_otp.*
import kotlinx.coroutines.launch

class VerifyOtpViewModel(private val authRepository: AuthRepository) : ViewModel() {


    private val navigationLd = MutableLiveData<String>()
    private val errorMessageLd = MutableLiveData<String>()

    val error: LiveData<String> = errorMessageLd
    val navigation: LiveData<String> = navigationLd

    fun onVerify(id: String, otp: String) {
        if (id.length != 4 && id.isBlank()) {
            errorMessageLd.value = "provide valid credential"
        } else {
            viewModelScope.launch {
                when (val response = authRepository.doVerify(getVerifyOtpRequest(id, otp))) {
                    is CustomResponse.Success -> navigationLd.value = response.data.message
                    is CustomResponse.Failure -> errorMessageLd.value =
                        response.error.message.toString()
                }
            }
        }

    }

    private fun getVerifyOtpRequest(id: String, otp: String): VerifyOtpRequest {
        return VerifyOtpRequest(
            id = id,
            otp = otp
        )
    }


}