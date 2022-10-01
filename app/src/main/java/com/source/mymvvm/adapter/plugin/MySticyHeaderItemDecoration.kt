package com.source.mymvvm.adapter.plugin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.source.mymvvm.R
import com.source.mymvvm.adapter.MyRvAdapter

class MySticyHeaderItemDecoration(val context:Context) : RecyclerView.ItemDecoration() {

    private val driversHeight:Int = 100
    private val mPaint:Paint

    init {
        mPaint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.purple_700)
            isAntiAlias = true
            textSize = 50f
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        if(position==0){
            outRect.top = driversHeight
            return
        }


        var mlist = (parent.adapter as MyRvAdapter).mlist

        if(mlist[position-1].pCode != view.tag){
            outRect.top = driversHeight
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        var mlist = (parent.adapter as MyRvAdapter).mlist
        var layoutManager = parent.layoutManager
        for(i in 0..mlist.size-1){
            val cView = layoutManager?.findViewByPosition(i)
            val y = (cView?.top?.toFloat()?:0f) - mPaint.measureText(mlist[i].pCode.toString())/2-10f
            if(i==0){
                c.drawText(cView?.tag.toString(),20f,y,mPaint)
                continue
            }

            if(mlist[i].pCode != mlist[i-1].pCode){
                c.drawText(cView?.tag.toString(),20f,y,mPaint)
            }
        }
    }
}