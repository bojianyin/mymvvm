package com.source.mymvvm.viewModel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.source.mymvvm.model.EventContants
import com.source.mymvvm.model.EventModel
import com.source.mymvvm.repo.MainReqManger
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    val TAG:String = this::class.java.simpleName

    val account:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val password:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun doLogin(){

        viewModelScope.launch {
            if(TextUtils.isEmpty(account.value) || TextUtils.isEmpty(password.value)){
                mEventData.postValue(
                    EventModel(
                        EventContants.LOGIN_MESSAGE,
                        data = hashMapOf(MSG to "",TYPE to "vaild")
                    )
                )
                return@launch
            }
            val result = MainReqManger.login(account = account.value!!, password = password.value!!)
            mEventData.postValue(
                EventModel(
                    EventContants.LOGIN_MESSAGE,
                    data = hashMapOf(MSG to result.validmesg,if(result.flag) TYPE to "success" else TYPE to "error")
                )
            )
        }


    }


}