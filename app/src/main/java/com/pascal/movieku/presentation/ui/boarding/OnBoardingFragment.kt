package com.pascal.movieku.presentation.ui.boarding

import androidx.lifecycle.ViewModel
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, ViewModel>(
    FragmentOnBoardingBinding::inflate
) {
    override fun initView() {
        onView()
    }

    private fun onView() {
        binding.apply {
            val adapterBoard = OnBoardingAdapter(
                requireActivity().supportFragmentManager,
                lifecycle
            )
            vpOnBoarding.adapter = adapterBoard
        }
    }

    override fun observeData() { }
}