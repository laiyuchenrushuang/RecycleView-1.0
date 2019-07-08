package com.ly.recycleview_10

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.ly.recycleview_10.MyAdapter.MyViewHolder


/**
 * Created by ly on 2019/7/8 10:44
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class MyAdapter(private var mContext: Context? = null, private var list: ArrayList<String>? = null) : RecyclerView.Adapter<MyViewHolder>(),Filterable {
   private var mSourceList:ArrayList<String> ?= null
    override fun getFilter(): Filter {
        return object : Filter() {
            //执行过滤操作
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    //没有过滤的内容，则使用源数据
                    list = mSourceList
                } else {
                    val filteredList = ArrayList<String>()
                    for (index in 0 until  mSourceList!!.size) {
                        //这里根据需求，添加匹配规则
                        if (mSourceList!!.get(index).contains(charString.toLowerCase()) || mSourceList!!.get(index).contains(charString.toUpperCase())) {
                            filteredList.add(mSourceList!!.get(index))
                        }
                    }

                    list = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = list
                return filterResults
            }

            //把过滤后的值返回出来
            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                list = filterResults.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_adapter, parent, false)
        Log.d("lylog", "onCreateViewHolder = ")
        mSourceList = list
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("lylog", "onCreateViewHolder = " + list!!.size)
        return list!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("lylog", "get1 = ")
        holder!!.initItemView(list!!.get(position))
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        private var text: TextView? = null

        init {
            Log.d("lylog", "get2 = ")
            text = view.findViewById(R.id.text)
        }

        fun initItemView(get: String) {
            Log.d("lylog", "get = " + get)
            text!!.setText(get)
        }

    }

}