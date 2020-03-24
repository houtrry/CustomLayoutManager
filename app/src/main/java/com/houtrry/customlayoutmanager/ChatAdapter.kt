package com.houtrry.customlayoutmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author: houtrry
 * @time: 2020/3/23
 * @desc:
 */
class ChatAdapter : RecyclerView.Adapter<ChatViewHolder>() {
    private val data :MutableList<String> = ArrayList()

    fun setData(list: MutableList<String>) {
        data.clear()
        data.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (data.isEmpty()) 0 else data.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.textView.text = data[position]
    }
}