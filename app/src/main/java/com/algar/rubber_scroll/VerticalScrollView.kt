package com.algar.rubber_scroll

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.MotionEvent

class VerticalScrollView(context: Context, attrs: AttributeSet) : NestedScrollView(context, attrs) {

    private var scrolled: Boolean = false

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        val handled = super.onTouchEvent(ev)

        if (handled) {
            val parent = parent
            when (ev?.actionMasked) {
                MotionEvent.ACTION_MOVE -> if (!scrolled && parent != null) {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
                MotionEvent.ACTION_DOWN -> {
                    scrolled = false
                    parent?.requestDisallowInterceptTouchEvent(true)
                }
            }
        }
        return handled
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (t != oldt) {
            scrolled = true
        }
    }
}