package com.source.mymvvm.repo

import com.source.mymvvm.repo.control.MyRetrofit
import com.source.mymvvm.repo.services.ApiService
import com.source.mymvvm.repo.services.ResResult
import java.lang.Exception

class MainReqManger {
    companion object{
        private val TAG:String = "MainReqManger"

        suspend fun login(account:String, password:String):ResResult?{
            return try {
                MyRetrofit.instance
                    .getServices(ApiService::class.java)
                    .login(account,password)
            }catch (e:Exception){
                null
            }

        }



    }

}