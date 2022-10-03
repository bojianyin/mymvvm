package com.source.mymvvm.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.source.mymvvm.R
import com.source.mymvvm.view.fragment.RvFragment
import kotlinx.android.synthetic.main.activity_vp_tab.*

class VpTabActivity : AppCompatActivity() {

//    val listdata:ArrayList<Fragment> = arrayListOf()
//    var adapter:MyVpAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vp_tab)

//        adapter = MyVpAdapter(supportFragmentManager)
//        bpager.adapter = adapter
//
//        for(i in 0..3){
//            val fg = RvFragment()
//            listdata.add(fg)
//        }
//        adapter?.notifyDataSetChanged()

    }


//    inner class MyVpAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT){
//        override fun getCount(): Int {
//            return listdata.size
//        }
//
//        override fun getItem(position: Int): Fragment {
//            return listdata.get(position)
//        }
//
//    }
}