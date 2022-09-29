package com.source.mymvvm.repo

import android.util.Log
import com.source.mymvvm.repo.control.MyRetrofit
import com.source.mymvvm.repo.services.ApiService
import com.source.mymvvm.repo.services.ResResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class MainReqManger {
    companion object{
        private val TAG:String = "MainReqManger"

        suspend fun login(account:String, password:String):ResResult{
            return withContext(Dispatchers.IO){
                return@withContext MyRetrofit.instance
                    .getServices(ApiService::class.java)
                    .login(account,password)
                    .await()
            }
        }


    }

}