package com.source.mymvvm.view.activity

import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.source.mymvvm.R
import com.source.mymvvm.view.activity.base.BaseActivity
import com.source.mymvvm.databinding.ActivityMainBinding
import com.source.mymvvm.model.EventContants
import com.source.mymvvm.model.EventModel
import com.source.mymvvm.utils.KeyBoardUtil
import com.source.mymvvm.vm.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun getLayoutView(): Int = R.layout.activity_main

    override fun doInit() {
        super.doInit()

        dataBinding?.vm = mViewModel

        dataBinding?.activity = this
    }

    override fun getVMClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onEventMessage(event: EventModel) {
        super.onEventMessage(event)
        when(event.type){
            EventContants.LOGIN_MESSAGE -> {
                val info = event.data as HashMap<String,String>
                loginCallback(info)
            }
        }
    }

    fun loginCallback(info:HashMap<String,String>){

        KeyBoardUtil.close(this)

        hideLoading()

        when(info[mViewModel?.TYPE]){

            "success","error" -> {
                Toast.makeText(this,info[mViewModel?.MSG]?:"",Toast.LENGTH_SHORT).show()
                if("success".equals(info[mViewModel?.TYPE]))
                    startActivity(Intent(this,ListActivity::class.java))
            }
        }

    }


    fun doLogin(){
        //判空
        if(TextUtils.isEmpty(mViewModel?.account?.value) || TextUtils.isEmpty(mViewModel?.password?.value)){
            Toast.makeText(this,"不能为空！",Toast.LENGTH_SHORT).show()
            return
        }

        showLoading()

        mViewModel?.doLogin()


    }

}