package com.source.mymvvm.view.activity

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.previewlibrary.GPreviewActivity
import com.previewlibrary.GPreviewBuilder
import com.source.mymvvm.R
import com.source.mymvvm.bean.PreImageBean
import kotlinx.android.synthetic.main.activity_pre_image.*

class PreImageActivity : AppCompatActivity(), View.OnClickListener {

//    https://i2.hdslb.com/bfs/face/5b3ae19483b3bcb0bc1517a7824612d7fe57ec97.jpg
//    https://profile.csdnimg.cn/A/B/2/2_qq_37399372
//    https://rescdn.qqmail.com/qqmail/images/mailappqrcode.416214657d.png
    //图片数据
    private val imgAry = arrayOf("https://i2.hdslb.com/bfs/face/5b3ae19483b3bcb0bc1517a7824612d7fe57ec97.jpg","https://profile.csdnimg.cn/A/B/2/2_qq_37399372","https://rescdn.qqmail.com/qqmail/images/mailappqrcode.416214657d.png")
    private val aryView = arrayListOf<ImageView>()
    val list = arrayListOf<PreImageBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_image)


        img1.tag = 0
        img2.tag = 1
        img3.tag = 2

        Glide.with(this).load(imgAry[0]).into(img1)
        Glide.with(this).load(imgAry[1]).into(img2)
        Glide.with(this).load(imgAry[2]).into(img3)

        aryView.add(img1)
        aryView.add(img2)
        aryView.add(img3)



        val iterator = imgAry.withIndex().iterator()
        while (iterator.hasNext()){
            val obj = iterator.next()
            val current = obj.value
//            val index = obj.index
            val mPreImageBean = PreImageBean(current)


            list.add(mPreImageBean)
        }


        img1.setOnClickListener(this)
        img2.setOnClickListener(this)
        img3.setOnClickListener(this)

    }

    override fun onClick(it: View?) {
        val index = it!!.tag as Int
        val localary = IntArray(2)
        it.getLocationInWindow(localary)

        val mPreImageBean = list.get(index)
        mPreImageBean.cmBounds = Rect(
            localary[0],
            localary[1],
            localary[0]+it.width,
            localary[1]+it.height
        )

        GPreviewBuilder
            .from(this)
            .setCurrentIndex(index)
            .setData(list)
            .setDrag(false)
            .setFullscreen(false)
            .start()
    }



//需要 先初始化
// ZoomMediaLoader.getInstance().init(PreImageLoader())  PreImageLoader 实现 IZoomMediaLoader类 用来实现怎样加载图片

//启动activity 需要
//List<IThumbViewInfo> 这样的数据 其中 IThumbViewInfo是接口 需要自己的bean实现它
}