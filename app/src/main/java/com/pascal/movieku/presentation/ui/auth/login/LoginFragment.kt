package com.pascal.movieku.presentation.ui.auth.login

import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import com.pascal.movieku.R
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.request.LoginRequest
import com.pascal.movieku.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnLogin.setOnClickListener {
                authLoginUser()
            }
            tvForgetPassword.setOnClickListener {
                moveNav(R.id.action_loginFragment_to_forgetPasswordFragment)
            }
            tvCreateAccount.setOnClickListener {
                moveNav(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModelInstance.loginUserResult.collect {
                if (it is Resource.Success) {
                    moveNav(R.id.action_loginFragment_to_homeFragment)
                }
                else if (it is Resource.Error) {
                    showMessageSnackBar(true, it.message)
                }
            }
        }
    }

    private fun authLoginUser() {
        binding.apply {
            when {
                checkFormValidation() -> {
                    viewModelInstance.loginUser(
                        LoginRequest(
                            username = etUsername.text.toString(),
                            password = etPassword.text.toString()
                        )
                    )
                }
            }
        }
    }

    private fun checkFormValidation(): Boolean {
        binding.apply {
            var isValid = true
            val user = etUsername.text.toString()
            val pass = etPassword.text.toString()

            when {
                user.isEmpty() && pass.isEmpty() -> {
                    tfUsername.error = "Fill the username"
                    tfPassword.error = "Fill the password"
                    isValid = false
                }
                user.isEmpty() -> {
                    tfUsername.error = "Fill the username"
                    isValid = false
                }
                pass.isEmpty() -> {
                    tfPassword.error = "Fill the password"
                    isValid = false
                }
                else -> {
                    tfUsername.error = null
                    tfPassword.error = null
                }
            }
            return isValid
        }
    }
}