package com.houtrry.customlayoutmanager

import android.content.Context
import android.util.Log
import android.view.View
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
        log("===>>>itemCount: $itemCount, childCount: $childCount, $firstVisibilityPosition/$lastVisibilityPosition")
        if (itemCount == 0) {
            //没有Item，界面空着吧
            detachAndScrapAttachedViews(recycler!!)
            return
        }

        if (childCount == 0 && state!!.isPreLayout) {
            //state.isPreLayout()是支持动画的
            return
        }


        firstVisibilityPosition = 0
        lastVisibilityPosition = itemCount
        verticalOffset = 0;

        fillChild(recycler!!, state!!)

    }

    private var mScrollOffsetY = 0
    private var mTotalHeight = 0


    //1.计算child的位置并layout
    //2.处理滑动, 根据offset重新layout child
    //3.detach 回收 不可见的view

    private fun fillChild(recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {

//        var offsetY = height - mScrollOffsetY
//        log("===>>>offsetY $offsetY = height: $height - mScrollOffsetY $mScrollOffsetY")

        mTotalHeight = getTotalItemHeight(recycler)

        detachAndScrapAttachedViews(recycler!!)

        var offsetY =
            if (mTotalHeight > height) height - mScrollOffsetY else mTotalHeight - mScrollOffsetY

        var view: View
        for (i in 0 until itemCount) {

            view = recycler.getViewForPosition(i)

            addView(view)

            measureChildWithMargins(view, 0, 0)

            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)

            layoutDecorated(view, 0, offsetY - height, width, offsetY)

            offsetY -= height

        }
        return 0
    }

    private fun getTotalItemHeight(recycler: RecyclerView.Recycler): Int {
        var totalHeight = 0
        var view: View
        for (i in 0 until itemCount) {
            view = recycler.getViewForPosition(i)
            measureChildWithMargins(view, 0, 0)
            val height = getDecoratedMeasuredHeight(view)
            totalHeight += height
        }
        return totalHeight
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        log("===>>>scrollVerticallyBy, dy: $dy, mScrollOffsetY: $mScrollOffsetY, itemCount: $itemCount, childCount: $childCount")
        val scrollY = mScrollOffsetY + dy
        log("===>>>scrollVerticallyBy, 到顶了吗? ${getSpaceHeight()} - $mTotalHeight = ${getSpaceHeight() - mTotalHeight} --> $scrollY")
        val travel = when {
            scrollY > 0 -> {
                log("===>>>scrollVerticallyBy, 到底了!!!")
                -mScrollOffsetY
            }
            scrollY <= getSpaceHeight() - mTotalHeight -> {
                log("===>>>scrollVerticallyBy, 到顶了!!!   ${getSpaceHeight()} -$mTotalHeight - $mScrollOffsetY = ${getSpaceHeight() -mTotalHeight  - mScrollOffsetY} -> $dy")
                if (getSpaceHeight() < mTotalHeight) (getSpaceHeight() -mTotalHeight  - mScrollOffsetY) else 0
            }
            else -> dy
        }

        log("===>>>scrollVerticallyBy, travel: $travel, scrollY: $scrollY")

        mScrollOffsetY += travel

//        offsetChildrenVertical(-travel)
        fillChild(recycler!!, state!!)
        return travel
    }

    private fun log(message: String) {
        Log.d(TAG, message)
    }

    companion object {
        private const val TAG = "ChatLayoutManager"
    }

    private fun getSpaceHeight(): Int {
        return height - paddingBottom - paddingTop
    }
}