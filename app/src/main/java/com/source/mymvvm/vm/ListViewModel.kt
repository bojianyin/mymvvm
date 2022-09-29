package com.source.mymvvm.vm

import androidx.lifecycle.MutableLiveData
import com.source.mymvvm.bean.ListItemBean
import com.source.mymvvm.vm.base.BaseViewModel

class ListViewModel : BaseViewModel() {
    val listData:MutableLiveData<ArrayList<ListItemBean>> by lazy {
        MutableLiveData<ArrayList<ListItemBean>>(arrayListOf())
    }


    fun initData(){
        val list = listData.value
        list?.clear()
        for(i in 0 until 200){
            val listItemBean = ListItemBean("https://avatars.githubusercontent.com/u/26374588?v=4", "title$i", "content$i")
            list?.add(listItemBean)
        }

        listData.postValue(list)

    }
}