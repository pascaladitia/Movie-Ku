package com.pascal.movieku.presentation.ui.auth.register

import androidx.lifecycle.lifecycleScope
import com.pascal.movieku.R
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.request.RegisterRequest
import com.pascal.movieku.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(
    FragmentRegisterBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.apply {
            tvSignIn.setOnClickListener {
                moveNav(R.id.action_registerFragment_to_loginFragment)
            }
            btnRegister.setOnClickListener {
                authRegisterUser()
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModelInstance.registerUserResult.collect {
                if (it is Resource.Success) {
                    showMessageToast(true, it.message)
                    moveNav(R.id.action_registerFragment_to_loginFragment)
                }
                else if (it is Resource.Error) {
                    showMessageSnackBar(true, it.message)
                }
            }
        }
    }

    private fun authRegisterUser() {
        binding.apply {
            if (checkFormValidation()) {
                viewModelInstance.registerUser(
                    RegisterRequest(
                        name = etRegisterName.text.toString(),
                        imageProfile = null,
                        email = etRegisterEmail.text.toString(),
                        age = etRegisterAge.text.toString().toInt(),
                        phoneNumber = etRegisterPhoneNumber.text.toString(),
                        username = etRegisterUsername.text.toString(),
                        password = etRegisterPassword.text.toString()
                    )
                )
            }
        }
    }

    private fun checkFormValidation(): Boolean {
        binding.apply {
            var isValid = true
            when {
                etRegisterName.text.toString().isEmpty() -> {
                    tfRegisterName.error = "Fill the name"
                    isValid = false
                }
                etRegisterEmail.text.toString().isEmpty() -> {
                    tfRegisterEmail.error = "Fill the email"
                    isValid = false
                }
                etRegisterAge.text.toString().isEmpty() -> {
                    tfRegisterAge.error = "Fill the age"
                    isValid = false
                }
                etRegisterPhoneNumber.text.toString().isEmpty() -> {
                    tfRegisterPhoneNumber.error = "Fill the phone"
                    isValid = false
                }
                etRegisterUsername.text.toString().isEmpty() -> {
                    tfRegisterUsername.error = "Fill the username"
                    isValid = false
                }
                etRegisterPassword.text.toString().isEmpty() -> {
                    tfRegisterPassword.error = "Fill the password"
                    isValid = false
                }
                else -> {
                    tfRegisterName.error = null
                    tfRegisterEmail.error = null
                    tfRegisterAge.error = null
                    tfRegisterPhoneNumber.error = null
                    tfRegisterUsername.error = null
                    tfRegisterPassword.error = null
                }
            }
            return isValid
        }
    }
}