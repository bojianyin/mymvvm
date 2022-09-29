package com.source.mymvvm.repo.control

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class HttpClient private constructor() {

    var mClient:OkHttpClient

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        mClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)

            .connectTimeout(maxTime,TimeUnit.MILLISECONDS)
            .readTimeout(maxTime,TimeUnit.MILLISECONDS)
            .writeTimeout(maxTime,TimeUnit.MILLISECONDS)
            .build()
    }

    companion object{
        private val maxTime = 10*1000L
        val instance:HttpClient by lazy (LazyThreadSafetyMode.SYNCHRONIZED){
            HttpClient()
        }
    }
}