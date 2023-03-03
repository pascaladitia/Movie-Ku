package com.pascal.movieku.presentation.ui.boarding.screen

import androidx.lifecycle.lifecycleScope
import com.pascal.movieku.R
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.databinding.FragmentThirdScreenBinding
import com.pascal.movieku.presentation.ui.boarding.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdScreenFragment : BaseFragment<FragmentThirdScreenBinding, OnBoardingViewModel>(
    FragmentThirdScreenBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.btnStart.setOnClickListener {
            viewModelInstance.setBoarding()
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModelInstance.setBoardingResult.collect {
                if (it is Resource.Success) {
                    moveNav(R.id.action_onBoarding_to_loginFragment)
                }
            }
        }
    }
}