package com.example.tabedex.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tabedex.R

private val TAB_TITLES = arrayOf(
    "장치 제어",
    "보안 카메라",
    "테스트1"
)

private val FRAGMENTS = arrayOf(
    DeviceControlFragment(),
    SecureCameraFragment(),
    TestFragment()
)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return FRAGMENTS[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {

        return TAB_TITLES.size
    }
}