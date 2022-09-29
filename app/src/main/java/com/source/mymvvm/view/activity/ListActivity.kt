package com.source.mymvvm.view.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.source.mymvvm.R
import com.source.mymvvm.adapter.MyListAdapter
import com.source.mymvvm.databinding.ActivityListBinding
import com.source.mymvvm.view.activity.base.BaseActivity
import com.source.mymvvm.viewModel.ListViewModel

class ListActivity : BaseActivity<ActivityListBinding,ListViewModel>() {
    private val mList by lazy { dataBinding?.mList }
    private val mHeaderBack by lazy { dataBinding?.mTitleTool }
    private var mLinearLayoutManager:LinearLayoutManager? = null
    private var adapter:MyListAdapter? = null

    override fun getLayoutView(): Int = R.layout.activity_list

    override fun getVMClass(): Class<ListViewModel> {
        return ListViewModel::class.java
    }

    override fun doInit() {
        super.doInit()
        mLinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mList?.layoutManager = mLinearLayoutManager

        mList?.adapter = MyListAdapter().apply {
            adapter = this
        }

        mViewModel?.initData()

        mViewModel?.listData?.observe(this){
            adapter?.setData(it)
        }

        mHeaderBack?.setOnClickListener { finish() }

    }
}