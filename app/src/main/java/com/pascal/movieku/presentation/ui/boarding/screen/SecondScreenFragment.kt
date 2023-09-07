package com.pascal.movieku.presentation.ui.boarding.screen

import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.pascal.movieku.R
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.databinding.FragmentSecondScreenBinding

class SecondScreenFragment : BaseFragment<FragmentSecondScreenBinding, ViewModel>(
    FragmentSecondScreenBinding::inflate
) {
    override fun initView() {
        onView()
    }

    private fun onView() {
        binding.apply {
            tvNext.setOnClickListener {
                activity?.findViewById<ViewPager2>(R.id.vp_on_boarding)?.currentItem = 2
            }
        }
    }

    override fun observeData() { }
}