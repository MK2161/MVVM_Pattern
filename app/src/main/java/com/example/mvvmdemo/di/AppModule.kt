package com.example.mvvmdemo.di

import com.example.mvvmdemo.Repository.AuthRepository
import com.example.mvvmdemo.Repository.DocumentRepository
import com.example.mvvmdemo.Repository.NotificationRepository
import com.example.mvvmdemo.data.RetrofitClient
import com.example.mvvmdemo.preferenceManager.PreferenceManager
import com.example.mvvmdemo.ui.changePassword.ChangePasswordViewModel
import com.example.mvvmdemo.ui.chooseOption.ChooseOptionViewModel
import com.example.mvvmdemo.ui.document.DocumentViewModel
import com.example.mvvmdemo.ui.forgot.ForgotPasswordViewModel
import com.example.mvvmdemo.ui.login.LoginViewModel
import com.example.mvvmdemo.ui.notification.NotificationViewModel
import com.example.mvvmdemo.ui.verifyOtp.VerifyOtpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    private val viewModelModules = module {
        viewModel { LoginViewModel(get()) }
        viewModel { ForgotPasswordViewModel() }
        viewModel { ChooseOptionViewModel (get())}
        viewModel { VerifyOtpViewModel(get()) }
        viewModel { ChangePasswordViewModel(get())}
        viewModel { NotificationViewModel (get())}
        viewModel { DocumentViewModel(get()) }
    }

    private val repoModules = module {
        single { AuthRepository(get(), get()) }
        single { NotificationRepository(get(), get()) }
        single { DocumentRepository(get(),get()) }
    }

    private val commonModules = module {
        single { RetrofitClient.createAdapter() }
        single { PreferenceManager(androidContext()) }
    }

    fun appModules() = viewModelModules + repoModules + commonModules
}