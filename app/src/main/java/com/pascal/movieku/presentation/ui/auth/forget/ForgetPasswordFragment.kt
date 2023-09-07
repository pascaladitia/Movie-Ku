package com.pascal.movieku.presentation.ui.auth.forget

import androidx.lifecycle.lifecycleScope
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.request.ForgetPasswordRequest
import com.pascal.movieku.databinding.FragmentForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding, ForgetPasswordViewModel>(
    FragmentForgetPasswordBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnFind.setOnClickListener {
                viewModelInstance.findUser(
                    ForgetPasswordRequest(
                        username = etRegisterUsername.text.toString(),
                        email = etRegisterEmail.text.toString()
                    )
                )
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModelInstance.findUserResult.collect {
                if (it is Resource.Success) {
                    moveNav(
                        ForgetPasswordFragmentDirections
                        .actionForgetPasswordFragmentToNewPasswordFragment()
                        .setUsername(binding.etRegisterUsername.text.toString())
                    )
                }
                else if (it is Resource.Error) {
                    showMessageSnackBar(true, it.message)
                }
            }
        }
    }
}