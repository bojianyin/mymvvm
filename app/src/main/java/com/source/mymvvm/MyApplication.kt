package com.source.mymvvm

import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import com.previewlibrary.ZoomMediaLoader
import com.source.mymvvm.utils.PreImageLoader

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        ZoomMediaLoader.getInstance().init(PreImageLoader())
    }

    companion object{
        @JvmStatic
        lateinit var context: Context
    }

}