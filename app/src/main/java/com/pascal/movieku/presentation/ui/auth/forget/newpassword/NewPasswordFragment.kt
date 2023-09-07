package com.pascal.movieku.presentation.ui.auth.forget.newpassword

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.pascal.movieku.R
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.request.NewPasswordRequest
import com.pascal.movieku.databinding.FragmentNewPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding, NewPasswordViewModel>(
    FragmentNewPasswordBinding::inflate
) {
    private val args by navArgs<NewPasswordFragmentArgs>()

    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnSubmit.setOnClickListener {
                viewModelInstance.updateUser(
                    NewPasswordRequest(
                        username = args.username.toString(),
                        password = etConfirmPassword.text.toString()
                    )
                )
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModelInstance.updateUserResult.collect {
                if (it is Resource.Success) {
                    showMessageToast(true, it.message)
                    moveNav(R.id.action_newPasswordFragment_to_loginFragment)
                }
            }
        }
    }
}