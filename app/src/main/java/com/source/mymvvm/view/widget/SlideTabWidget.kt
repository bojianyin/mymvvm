package com.source.mymvvm.view.widget

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginLeft
import com.source.mymvvm.R
import kotlinx.android.synthetic.main.widget_slide_tab.view.*
import kotlin.collections.ArrayList

class SlideTabWidget @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : RoundRadiusWidget(context, attrs) {

    private var mlistData:ArrayList<TabEntry<*>> = arrayListOf()

    private var tabLayout:LinearLayout? = null
    private var prevView:View? = null
    //滑动线
    private var line: View? = null

    var listener:OnClickListener? = null

    //现在选中的索引
    var cPosition:Int? = null

    private val lineAnimate:ValueAnimator by lazy {
        ValueAnimator.ofInt(0,0)
    }

    init {
        setRadius(dptopx(10f).toInt())
        setBackgroundResource(R.drawable.bg_tab_radius)
        initLayout()
    }

    private fun initLayout() {
        val layout = LayoutInflater.from(context).inflate(R.layout.widget_slide_tab,this,true)
        line = layout.findViewById(R.id.line)

        tabLayout = layout.findViewById(R.id.contentLayout)

        lineAnimate?.addUpdateListener {
            val layoutParams = LayoutParams(line?.layoutParams)
            layoutParams.width = it.animatedValue as Int
            layoutParams.addRule(ALIGN_PARENT_BOTTOM)
            line?.layoutParams = layoutParams

        }

    }


    fun <T> add(entry:TabEntry<T>):SlideTabWidget{
        mlistData.add(entry)
        return this
    }

    fun commit(){
        cPosition = 0
        for((index,entry) in mlistData.withIndex()){
            val view = LayoutInflater.from(context).inflate(R.layout.widget_tab_item,this,false)

            val title:TextView = view.findViewById(R.id.titleTab)
            title.text = entry.text

            view.setOnClickListener {
                if(cPosition==null) {
                    throw ExceptionInInitializerError("no commit!!!")
                }
                if(cPosition == index){
                    listener?.reClick(cPosition!!,it)
                    return@setOnClickListener
                }
                cPosition = index
                listener?.onClick(cPosition!!,it)
                moveView(it)
            }

            tabLayout?.addView(view)

            if(index==0){
                view?.post {
                    moveView(view)
                }

            }
        }

    }

    fun moveView(it:View){
        val location = IntArray(2)
        it.getLocationInWindow(location)

        val pMargin = marginLeft


        //改变位置x
        line?.animate()?.translationX(location[0].toFloat()-pMargin+dptopx(12f)+scrollBar.scrollX)?.setDuration(300)?.start()

        //改变宽度
        lineAnimate.setIntValues(line!!.width,it.width-(dptopx(12f*2).toInt()))
        lineAnimate.duration = 300
        lineAnimate.start()


        /*
        * item距离控件的left距离 - 屏幕/2
        * */

        val pleft = it.left
        val screenW = context.resources.displayMetrics.widthPixels
        val v = pleft - screenW / 2 + it.width/2
        scrollBar.smoothScrollTo(v,0)

        val select_tab_ani = AnimationUtils.loadAnimation(context, R.anim.select_tab_ani)
        select_tab_ani.fillAfter = true
        val unselect_tab_ani = AnimationUtils.loadAnimation(context, R.anim.unselect_tab_ani)
        unselect_tab_ani.fillAfter = true
        if(prevView!=null){
            prevView?.startAnimation(unselect_tab_ani)
        }

        it.startAnimation(select_tab_ani)
        prevView = it
    }


    fun dptopx(dp:Float):Float{
        val density = context.resources.displayMetrics.density
        return dp * density + 0.5f
    }

    data class TabEntry<T>(val tid:Int) {
        var text:String? = null
        var data:T? = null
    }

    interface OnClickListener{
        fun onClick(position:Int,view:View)
        fun reClick(position:Int,view:View){
            Log.d("SlideTabWidget","reClick>>>")
        }
    }
}