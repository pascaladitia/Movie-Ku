package com.pascal.movieku.presentation.ui.boarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pascal.movieku.presentation.ui.boarding.screen.FirstScreenFragment
import com.pascal.movieku.presentation.ui.boarding.screen.SecondScreenFragment
import com.pascal.movieku.presentation.ui.boarding.screen.ThirdScreenFragment

class OnBoardingAdapter(fm: FragmentManager, ls: Lifecycle) : FragmentStateAdapter(fm,ls) {
    private val listFragment = arrayListOf(
        FirstScreenFragment(),
        SecondScreenFragment(),
        ThirdScreenFragment()
    )
    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}