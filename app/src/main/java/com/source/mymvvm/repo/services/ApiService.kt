package com.source.mymvvm.repo.services

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("/user/AjaxUserLogin")
    fun login(@Field("UserName") account:String,@Field("Password") pwd:String):Call<ResResult>
}