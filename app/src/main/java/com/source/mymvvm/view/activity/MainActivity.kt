package com.source.mymvvm.view.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.source.mymvvm.R
import com.source.mymvvm.view.activity.base.BaseActivity
import com.source.mymvvm.databinding.ActivityMainBinding
import com.source.mymvvm.model.EventContants
import com.source.mymvvm.model.EventModel
import com.source.mymvvm.utils.KeyBoardUtil
import com.source.mymvvm.view.widget.SlideTabWidget
import com.source.mymvvm.vm.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    private val mSlideTab:SlideTabWidget? by lazy { dataBinding?.mSlideTab }
    override fun getLayoutView(): Int = R.layout.activity_main

    override fun doInit() {
        super.doInit()

        dataBinding?.vm = mViewModel

        dataBinding?.activity = this

        mSlideTab?.apply {
            add(
                SlideTabWidget.TabEntry<String>(1).apply{
                    text = "tab1"
                    data = "hello"
                }
            )
            add(
                SlideTabWidget.TabEntry<String>(2).apply{
                    text = "tab2"
                    data = "hello2"
                }
            )
            add(
                SlideTabWidget.TabEntry<String>(3).apply{
                    text = "tab3dasdsa"
                    data = "hello3"
                }
            )
            add(
                SlideTabWidget.TabEntry<String>(4).apply{
                    text = "tab4"
                    data = "hello4"
                }
            )
            add(
                SlideTabWidget.TabEntry<String>(4).apply{
                    text = "tab4"
                    data = "hello4"
                }
            )
            add(
                SlideTabWidget.TabEntry<String>(4).apply{
                    text = "tab4"
                    data = "hello4"
                }
            )
            add(
                SlideTabWidget.TabEntry<String>(4).apply{
                    text = "tab4"
                    data = "hello4"
                }
            )
            add(
                SlideTabWidget.TabEntry<String>(4).apply{
                    text = "tab4"
                    data = "hello4"
                }
            )
        }?.commit()


        mSlideTab?.listener = object : SlideTabWidget.OnClickListener{
            override fun onClick(position: Int, view: View) {
                Log.d("Main","onClick"+position)
                val mClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                if(mClipboardManager.hasPrimaryClip()){
                    Toast.makeText(this@MainActivity,mClipboardManager.primaryClip?.getItemAt(0)?.text,Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@MainActivity,"没有复制的内容",Toast.LENGTH_SHORT).show()
                }

            }

            override fun reClick(position: Int, view: View) {
                super.reClick(position, view)
                Log.d("Main","reClick"+position)

                val mClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                mClipboardManager.setPrimaryClip(ClipData.newPlainText("ceshi","ceshi"))

            }
        }

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