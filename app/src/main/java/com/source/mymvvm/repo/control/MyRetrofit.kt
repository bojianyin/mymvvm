package com.source.mymvvm.repo.control

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetrofit private constructor(){
    private val servicesMap by lazy { hashMapOf<String,Any>() }

    fun createRetrofit(baseStr:String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseStr)
            .client(HttpClient.instance.mClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getServices(classType:Class<T>,baseStr: String = defBaseStr):T{
        val simpleName = classType.simpleName
        return if(servicesMap.containsKey(simpleName)){
            servicesMap[simpleName] as T
        }else{
            //如果map里 不存在 retrofit重新创建 指定services接口
            val cRetrofit = createRetrofit(baseStr)
            val api = cRetrofit.create(classType)
            servicesMap.put(simpleName,api as Any)
            api
        }
    }

    companion object{
        private val defBaseStr:String = "http://www.tuohuangzu.com"
        val instance:MyRetrofit by lazy (LazyThreadSafetyMode.SYNCHRONIZED){ MyRetrofit() }

    }
}