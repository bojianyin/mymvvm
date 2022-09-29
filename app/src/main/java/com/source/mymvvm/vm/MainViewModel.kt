package com.source.mymvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.source.mymvvm.model.EventContants
import com.source.mymvvm.model.EventModel
import com.source.mymvvm.repo.MainReqManger
import com.source.mymvvm.vm.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    val TAG:String = this::class.java.simpleName

    val account:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val password:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    //登陆操作
    fun doLogin(){

        viewModelScope.launch {

            val result = MainReqManger.login(account = account.value!!, password = password.value!!) ?: return@launch

            //发送结果
            mEventData.postValue(
                EventModel(
                    EventContants.LOGIN_MESSAGE,
                    data = hashMapOf(MSG to result?.validmesg,if(result?.flag == true) TYPE to "success" else TYPE to "error")
                )
            )

        }


    }


}