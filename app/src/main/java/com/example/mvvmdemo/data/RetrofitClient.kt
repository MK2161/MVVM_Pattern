package com.example.mvvmdemo.data

import com.example.mvvmdemo.preferenceManager.PreferenceManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient : KoinComponent{

    private val preferenceManager : PreferenceManager by inject()

    private const val BASE_URL = "https://ecs-dsapi-staging.digi-val.com/api/"

    fun createAdapter(): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }

    private fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.addHeader("Content-Type", "application/json")
                builder.addHeader("_institution_id", "5e5d0f1a15b4d600173d5692")
                // If token has been saved, add it to the request
                preferenceManager.getAccessToken()?.let {
                    builder.addHeader("Authorization", "Bearer $it")

                }

                builder.addHeader("Accept-Language", "en")
                return@addInterceptor chain.proceed(builder.build())
            }
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }
}