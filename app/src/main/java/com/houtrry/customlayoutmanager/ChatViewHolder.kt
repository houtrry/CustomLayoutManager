package com.houtrry.customlayoutmanager

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author: houtrry
 * @time: 2020/3/23
 * @desc:
 */
class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView by lazy {
        itemView.findViewById<TextView>(R.id.tv_item_chat)
    }
}