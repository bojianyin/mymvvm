package com.source.mymvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.source.mymvvm.R
import com.source.mymvvm.bean.ListItemBean
import com.source.mymvvm.databinding.ItemListLayoutBinding

class MyListAdapter(private var data:ArrayList<ListItemBean> = arrayListOf()) :
    RecyclerView.Adapter<MyListAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val viewBinding:ItemListLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_list_layout,parent,false)

        return MyHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.itemData = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    open class MyHolder(val binding:ItemListLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(data:ArrayList<ListItemBean>){
        this.data = data
        notifyDataSetChanged()
    }
}