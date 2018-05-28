package com.algar.rubber_scroll

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: VerticalViewPager = findViewById(R.id.vvp)
        val adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }
}

class ScreenSlidePagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return SlideFragment()
    }

    override fun getCount(): Int {
        return 5
    }
}
