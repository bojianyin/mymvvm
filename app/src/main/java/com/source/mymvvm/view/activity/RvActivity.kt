package com.source.mymvvm.view.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.promeg.pinyinhelper.Pinyin
import com.google.gson.Gson
import com.source.mymvvm.R
import com.source.mymvvm.adapter.MyRvAdapter
import com.source.mymvvm.adapter.plugin.MySticyHeaderItemDecoration
import com.source.mymvvm.adapter.plugin.StickHeaderDecoration
import com.source.mymvvm.adapter.plugin.TitleItemDecoration
import com.source.mymvvm.bean.AreaItemBean
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_rv.*
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset


class RvActivity : AppCompatActivity() {
    var TAG = this::class.java.simpleName
    var layoutManager: LinearLayoutManager? = null
    var dataArrayList:ArrayList<AreaItemBean> = arrayListOf()
    val adapter:MyRvAdapter by lazy { MyRvAdapter(dataArrayList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRv?.layoutManager = layoutManager
        mRv?.adapter = adapter
        mRv?.setHasFixedSize(true)
        mRv?.addItemDecoration(StickHeaderDecoration(this))

        val dataInputStream = assets.open("area.json")

        val bt = ByteArray(1024)
        val byteArrayOutputStream = ByteArrayOutputStream()
        var len = 0

        do{
            len = dataInputStream.read(bt)
            if(len == -1) break

            byteArrayOutputStream.write(bt,0,len)
        } while(len!=-1)

        val data = String(byteArrayOutputStream.toByteArray(), Charset.defaultCharset())

        dataInputStream.close()
        byteArrayOutputStream.close()

        val jsonObject = JSONObject(data)

        val countryList = jsonObject.optJSONArray("countryList")

        for(i in 0 until countryList.length()){
            val current = countryList[i].toString()
            val data = Gson().fromJson(current, AreaItemBean::class.java)
            data.pCode = Pinyin.toPinyin(data.cnName.first()).first()
            val isHasCodeList = dataArrayList.filter {
                it.pCode == data.pCode
            }
            val noHas = isHasCodeList.isEmpty()
            data.isHead = noHas

            dataArrayList.add(data)

        }

        dataArrayList.sortBy {
            it.pCode
        }

        adapter.setData(dataArrayList)


        mRv?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val view = recyclerView.getChildAt(0) as TextView
                Log.d(TAG, "onScrolled: ${view.top}")
            }
        })


    }



}