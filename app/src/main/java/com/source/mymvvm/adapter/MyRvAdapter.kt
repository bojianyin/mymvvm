package com.source.mymvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.source.mymvvm.R
import com.source.mymvvm.bean.AreaItemBean

class MyRvAdapter(data:ArrayList<AreaItemBean>) : RecyclerView.Adapter<MyRvAdapter.MyHolder>(){
    var mlist:ArrayList<AreaItemBean>

    init {
        mlist = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            android.R.layout.simple_list_item_1,
            parent,
            false
        )

        view.setBackgroundColor(ContextCompat.getColor(parent.context,R.color.teal_700))
        return MyHolder(view,viewType)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val txtView = holder.getView<TextView>(android.R.id.text1)
        holder.itemView.setTag(mlist[position].pCode)
        txtView.text = mlist[position].cnName
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    fun setData(data:ArrayList<AreaItemBean>){
        this.mlist = data
        notifyDataSetChanged()
    }

    fun isItemHeader(position: Int):Boolean{
        return mlist[position].isHead
    }

    fun getGroupName(position: Int):String{
        return mlist[position].pCode.toString()
    }

    class MyHolder(private val view: View,val viewType: Int):RecyclerView.ViewHolder(view){

        fun <T> getView(id: Int):T{
            return view.findViewById(id) as T
        }

    }






}