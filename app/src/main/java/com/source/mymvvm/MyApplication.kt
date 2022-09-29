package com.source.mymvvm

import android.app.Application
import android.app.ProgressDialog
import android.content.Context

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

    }

    companion object{
        @JvmStatic
        lateinit var context: Context
    }

}