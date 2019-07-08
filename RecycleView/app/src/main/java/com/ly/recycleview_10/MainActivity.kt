package com.ly.recycleview_10

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val str = arrayOf("ALaiyu", "apple", "apple juice", "apple pie", "abalone", "bread", "brandy", "Blueberry", "Banana", "chocolate", "cake", "chicken", "cheese", "Durian", "Dim Sam", "Dumpling", "duck", "egg", "English muffin", "eggplant", "French toast", "fish", "fig", "fruit")
    private val mList = ArrayList<String>()
    private var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        initEvent()
        var ll = LinearLayoutManager(this)
        recycle.layoutManager = ll
        adapter = MyAdapter(this, mList)
        Log.d("lylog", "mList = " + mList.size)
        recycle!!.adapter = adapter
    }

    private fun initEvent() {
        edit_query!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter!!.getFilter().filter(sequence.toString())
            }

        })
        shuaxin!!.setOnRefreshListener(object :OnRefreshListener{
            override fun onRefresh(refreshlayout: RefreshLayout?) {
                Log.d("lylog","refreshed")
                refreshlayout!!.finishRefresh()
            }

        })
        shuaxin!!.setOnLoadmoreListener(object :OnLoadmoreListener{
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                Log.d("lylog","Loaded")
                refreshlayout!!.finishLoadmore()
            }
        })
    }

    private fun initList() {
        for (i in 0 until str!!.size) {
            mList.add(str[i])
        }
    }
}
