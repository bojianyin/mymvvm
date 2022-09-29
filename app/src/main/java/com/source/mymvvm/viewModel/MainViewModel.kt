package com.source.mymvvm.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.source.mymvvm.model.EventContants
import com.source.mymvvm.model.EventModel

class MainViewModel : BaseViewModel() {
    val TAG:String = this::class.java.simpleName

    val account:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val password:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun doLogin(){
        Log.d(TAG, "doLogin: ${account.value} ${password.value}")
        mEventData.postValue(
            EventModel(
                EventContants.LOGIN_SUCCESS,
                data = "登陆成功！"
            )
        )
    }


}