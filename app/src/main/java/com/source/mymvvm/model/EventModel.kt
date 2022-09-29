package com.source.mymvvm.model

class EventModel(val type:String,val data:Any?)

object EventContants{
    const val LOGIN_MESSAGE = "EVENT_LOGIN_MESSAGE"
    const val LOADING       = "LOADING"
}