package com.algar.rubber_scroll

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VerticalViewPager (context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    init {
        setPageTransformer(false, DefaultTransformer())
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    private fun swapTouchEvent(event: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()

        val swappedX = event.y / height * width
        val swappedY = event.x / width * height

        event.setLocation(swappedX, swappedY)

        return event
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        val intercept = super.onInterceptTouchEvent(swapTouchEvent(event))
        swapTouchEvent(event)
        return intercept
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapTouchEvent(ev))
    }

    inner class DefaultTransformer : ViewPager.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.translationX = view.width * -position
            val yPosition = position * view.height
            view.translationY = yPosition
        }
    }
}
