package com.source.mymvvm.view.activity.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.source.mymvvm.model.EventContants
import com.source.mymvvm.model.EventModel
import com.source.mymvvm.view.dialog.LoadingDialog
import com.source.mymvvm.vm.base.BaseViewModel

abstract class BaseActivity<T : ViewDataBinding,VM : BaseViewModel> : AppCompatActivity() {

    var dataBinding:T? = null
    var mViewModel:VM? = null

    val loadingDialog:LoadingDialog? by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, getLayoutView())

        dataBinding?.lifecycleOwner = this

        mViewModel = ViewModelProvider(this).get(getVMClass())

        doInit()
    }

    @LayoutRes
    abstract fun getLayoutView():Int

    open fun doInit(){

        mViewModel?.mEventData?.observe(this) {
            if(it.type==EventContants.LOADING){
                if(it.data is Boolean && it.data){

                    showLoading()

                }else{
                    hideLoading()
                }
                return@observe
            }
            onEventMessage(it)
        }

    }

    abstract fun getVMClass():Class<VM>

    open fun onEventMessage(event:EventModel){

    }

    fun showLoading(){
        if(loadingDialog?.isShowing()==true) return
        loadingDialog?.show()
    }

    fun hideLoading(){
        loadingDialog?.dismiss()
    }
}