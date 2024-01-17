package com.example.androidapp.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = ContentBoardingFragment()
        fragment.arguments = Bundle().apply {
            putInt(ContentBoardingFragment.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }

}