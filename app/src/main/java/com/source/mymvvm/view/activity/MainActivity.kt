package com.source.mymvvm.view.activity

import android.content.Intent
import android.widget.Toast
import com.source.mymvvm.R
import com.source.mymvvm.view.activity.base.BaseActivity
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
            EventContants.LOGIN_MESSAGE -> {
                val info = event.data as HashMap<String,String>
                loginHandler(info)
            }
        }
    }

    fun loginHandler(info:HashMap<String,String>){

        KeyBoardUtil.close(this)
        when(info[mViewModel?.TYPE]){
            "vaild" -> {
                Toast.makeText(this,"用户名密码不能为空!",Toast.LENGTH_SHORT).show()
            }
            "success","error" -> {
                Toast.makeText(this,info[mViewModel?.MSG]?:"",Toast.LENGTH_SHORT).show()
                if("success".equals(info[mViewModel?.TYPE]))
                    startActivity(Intent(this,ListActivity::class.java))
            }
        }
    }

}