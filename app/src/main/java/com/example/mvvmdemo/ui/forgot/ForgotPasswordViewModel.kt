package com.example.mvvmdemo.ui.forgot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgotPasswordViewModel : ViewModel() {

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    private val navigationEmailLd = MutableLiveData<String>()
    private val errorMessageLd = MutableLiveData<String>()

    val navigationEmail: LiveData<String> = navigationEmailLd
    val error: LiveData<String> = errorMessageLd

    fun onVerifyEmail(email: String?) {
        if (email?.matches(emailPattern.toRegex()) != true) {
            errorMessageLd.value = "Provide Valid Credential"
        } else {
            navigationEmailLd.value = Unit.toString()

        }
    }
}

