package com.source.mymvvm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.promeg.pinyinhelper.Pinyin
import com.google.gson.Gson
import com.source.mymvvm.R
import com.source.mymvvm.adapter.MyRvAdapter
import com.source.mymvvm.adapter.plugin.StickHeaderDecoration
import com.source.mymvvm.bean.AreaItemBean
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset


/**
 * A simple [Fragment] subclass.
 * Use the [RvFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RvFragment : Fragment() {
    var layoutManager: LinearLayoutManager? = null
    var dataArrayList:ArrayList<AreaItemBean> = arrayListOf()
    val adapter: MyRvAdapter by lazy { MyRvAdapter(dataArrayList) }
    var mRv:RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rv, container, false)
        mRv = view.findViewById(R.id.mRv)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mRv?.layoutManager = layoutManager
        mRv?.adapter = adapter
        mRv?.setHasFixedSize(true)
//        mRv?.addItemDecoration(StickHeaderDecoration(requireContext()))

        val dataInputStream = requireActivity().assets.open("area.json")

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
            }
        })

        return view
    }

}