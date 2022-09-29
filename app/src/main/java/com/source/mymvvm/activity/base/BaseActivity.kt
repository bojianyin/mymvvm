package com.source.mymvvm.activity.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.source.mymvvm.model.EventModel
import com.source.mymvvm.viewModel.BaseViewModel

abstract class BaseActivity<T : ViewDataBinding,VM:BaseViewModel> : AppCompatActivity() {

    var dataBinding:T? = null
    var mViewModel:VM? = null

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

        mViewModel?.mEventData?.observe(this, {
            onEventMessage(it)
        })

    }

    abstract fun getVMClass():Class<VM>

    open fun onEventMessage(event:EventModel){

    }
}