package com.example.mvvmdemo.myApplication

import android.app.Application
import com.example.mvvmdemo.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication:Application() {


    override fun onCreate() {
        super.onCreate()
        configKoin()
    }
    private fun configKoin(){
        startKoin{
            androidContext(this@MyApplication)
            modules(AppModule.appModules())
        }
    }
}
