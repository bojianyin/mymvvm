package com.source.mymvvm.viewModel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.source.mymvvm.model.EventContants
import com.source.mymvvm.model.EventModel
import com.source.mymvvm.repo.MainReqManger
import com.source.mymvvm.repo.services.ResResult
import kotlinx.coroutines.delay
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
            //判空
            if(TextUtils.isEmpty(account.value) || TextUtils.isEmpty(password.value)){
                mEventData.postValue(
                    EventModel(
                        EventContants.LOGIN_MESSAGE,
                        data = hashMapOf(MSG to "",TYPE to "vaild")
                    )
                )
                return@launch
            }
            var result: ResResult? = null

            loadingBlock {

                result = MainReqManger.login(account = account.value!!, password = password.value!!) ?: return@loadingBlock

            }
            delay(500)

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