package com.source.mymvvm.activity

import android.widget.Toast
import com.source.mymvvm.R
import com.source.mymvvm.activity.base.BaseActivity
import com.source.mymvvm.databinding.ActivityMainBinding
import com.source.mymvvm.model.EventContants
import com.source.mymvvm.model.EventModel
import com.source.mymvvm.utils.KeyBoardUtil
import com.source.mymvvm.viewModel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun getLayoutView(): Int = R.layout.activity_main

    override fun doInit() {
        super.doInit()

        dataBinding?.vm = mViewModel
    }

    override fun getVMClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onEventMessage(event: EventModel) {
        super.onEventMessage(event)

        when(event.type){
            EventContants.LOGIN_SUCCESS -> {
                KeyBoardUtil.close(this)
                Toast.makeText(this,event.data as String,Toast.LENGTH_SHORT).show()
            }
        }
    }

}