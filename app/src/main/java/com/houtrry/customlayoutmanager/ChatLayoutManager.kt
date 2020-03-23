package com.houtrry.customlayoutmanager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * @author: houtrry
 * @time: 2020/3/23
 * @desc:
 */
class ChatLayoutManager : LinearLayoutManager {

    //https://blog.csdn.net/zxt0601/article/details/52956504
    constructor(context: Context?) : super(context)
    constructor(
        context: Context?, orientation: Int,
        reverseLayout: Boolean
    ) : super(context, orientation, reverseLayout)

    constructor(
        context: Context?, attrs: AttributeSet?,
        defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

}