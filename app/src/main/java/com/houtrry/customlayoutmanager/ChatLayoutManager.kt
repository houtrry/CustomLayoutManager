package com.houtrry.customlayoutmanager

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author: houtrry
 * @time: 2020/3/23
 * @desc:
 */
class ChatLayoutManager : RecyclerView.LayoutManager {

    private var firstVisibilityPosition: Int = 0
    private var lastVisibilityPosition: Int = 0
    private var verticalOffset: Int = 0

    constructor() {

    }

    //https://blog.csdn.net/zxt0601/article/details/52956504
    constructor(context: Context?) {

    }
//    constructor(
//        context: Context?, orientation: Int,
//        reverseLayout: Boolean
//    ) : super(context, orientation, reverseLayout)
//
//    constructor(
//        context: Context?, attrs: AttributeSet?,
//        defStyleAttr: Int, defStyleRes: Int
//    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        log("itemCount: $itemCount, childCount: $childCount")
        if (itemCount == 0) {
            //没有Item，界面空着吧
            detachAndScrapAttachedViews(recycler!!)
            return
        }

        if (childCount == 0 && state!!.isPreLayout) {
            //state.isPreLayout()是支持动画的
            return
        }

        detachAndScrapAttachedViews(recycler!!)

        firstVisibilityPosition = 0
        lastVisibilityPosition = itemCount
        verticalOffset = 0;

        fillChild(recycler!!, state!!)

    }

    private fun fillChild(recycler: RecyclerView.Recycler, state: RecyclerView.State) {

        var offsetY = height

        for (i in 0 until itemCount) {

            var view = recycler.getViewForPosition(i)

            measureChildWithMargins(view, 0, 0)

            val height = getDecoratedMeasuredHeight(view)

            offsetY -= height
            if (offsetY <= 0) {
                break
            }
        }

        offsetY = if (offsetY > 0) {
            height - offsetY
        } else {
            height
        }

        for (i in 0 until itemCount) {

            var view = recycler.getViewForPosition(i)

            addView(view)

            measureChildWithMargins(view, 0, 0)

            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)

            layoutDecorated(view, 0, offsetY - height, width, offsetY)

            offsetY -= height
        }

    }
}