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