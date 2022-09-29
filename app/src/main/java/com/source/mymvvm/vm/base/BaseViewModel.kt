package com.source.mymvvm.vm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.source.mymvvm.model.EventContants
import com.source.mymvvm.model.EventModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {
    val TYPE = "TYPE"
    val MSG  = "MSG"
    val mEventData:MutableLiveData<EventModel> by lazy {
        MutableLiveData()
    }

    suspend fun loadingBlock(block:suspend ()->Unit){
        withContext(Dispatchers.IO){
            //开始loading
            mEventData.postValue(
                EventModel(
                    EventContants.LOADING,
                    data = true
                )
            )

            block.invoke()

            //取消loading
            mEventData.postValue(
                EventModel(
                    EventContants.LOADING,
                    data = false
                )
            )
        }
    }

}