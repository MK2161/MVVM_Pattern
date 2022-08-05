package com.example.mvvmdemo.ui.login

import androidx.lifecycle.*
import com.example.mvvmdemo.Repository.AuthRepository
import com.example.mvvmdemo.Repository.NotificationRepository
import com.example.mvvmdemo.handler.CustomResponse
import com.example.mvvmdemo.model.CredentialModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    private val navigationLd = MutableLiveData<Unit>()
    private val errorMessageLd = MutableLiveData<String>()

    val navigation: LiveData<Unit> = navigationLd
    val error: LiveData<String> = errorMessageLd

    fun onVerifyLogin(email: String?, password: String?) {
        if (password.isNullOrBlank()) {
            errorMessageLd.value = "Provide Valid Credential"
        } else if (email?.matches(emailPattern.toRegex()) != true && email.isNullOrBlank()) {
            errorMessageLd.value = "Provide Valid Credential"
        } else {
            viewModelScope.launch {
                when (val response = authRepository.doLogin(getCredentialModel(email, password))) {
                    is CustomResponse.Success -> navigationLd.value = Unit
                    is CustomResponse.Failure -> errorMessageLd.value =
                        response.error.message.toString()
                }
            }
        }
    }

    private fun getCredentialModel(email: String, password: String): CredentialModel {
        return CredentialModel(
            email = email,
            password = password,
            userType = "staff",
            deviceType = "android"
        )
    }
}