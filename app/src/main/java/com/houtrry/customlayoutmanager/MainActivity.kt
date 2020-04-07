package com.houtrry.customlayoutmanager

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val initSize = 0
    private val list: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = ChatLayoutManager()
        val adapter = ChatAdapter()

        for (index in 0..initSize) {
            list.add("第${index}条数据")
        }
        adapter.setData(list)
        recyclerView.adapter = adapter

        tvAddData.setOnClickListener {
            list.add("第${list.size}条数据")
            adapter.setData(list)
            adapter.notifyDataSetChanged()
        }

    }
}
