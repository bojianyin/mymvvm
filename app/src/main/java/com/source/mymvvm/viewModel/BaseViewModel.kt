package com.source.mymvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.source.mymvvm.model.EventModel

open class BaseViewModel : ViewModel() {
    val mEventData:MutableLiveData<EventModel> by lazy {
        MutableLiveData()
    }
}