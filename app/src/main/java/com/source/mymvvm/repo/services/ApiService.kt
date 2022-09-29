package com.source.mymvvm.repo.services

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("/user/AjaxUserLogin")
    suspend fun login(@Field("UserName") account:String,@Field("Password") pwd:String):ResResult
}