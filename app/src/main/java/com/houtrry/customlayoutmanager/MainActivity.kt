package com.houtrry.customlayoutmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val INIT_DATA_COUNT = 10
    val data = listOf<String>(
        "第1条数据",
        "第2条数据",
        "第3条数据",
        "第4条数据",
        "第5条数据",
        "第6条数据"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ChatAdapter()
        recyclerView.adapter = adapter

        tvAddData.setOnClickListener {

        }
    }
}
