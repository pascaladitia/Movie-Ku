package com.pascal.movieku.presentation.ui.homepage.profile.edit

import androidx.lifecycle.lifecycleScope
import com.pascal.movieku.R
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.data.local.model.request.EditProfileRequest
import com.pascal.movieku.databinding.FragmentEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>(
    FragmentEditProfileBinding::inflate
) {
    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModelInstance.getUser()
    }

    private fun onClick() {
        binding.apply {
            ivBackProfile.setOnClickListener {
                moveNav(R.id.action_editProfileFragment_to_profileUserFragment)
            }

            btnSave.setOnClickListener {
                saveDataProfile()
            }
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModelInstance.getUserResult.collect {
                    if (it is Resource.Success) {
                        setDataEditProfile(it.data)
                    }
                }
            }

            launchWhenStarted {
                viewModelInstance.updateProfileUserResult.collect {
                    if (it is Resource.Success) {
                        moveNav(R.id.action_editProfileFragment_to_profileUserFragment)
                    }
                }
            }
        }
    }

    private fun setDataEditProfile(data: UserEntity?) {
        binding.apply {
            data?.let {
                etEditName.setText(it.name)
                etEditEmail.setText(it.email)
                etEditAge.setText(it.age.toString())
                etEditPhoneNumber.setText(it.phoneNumber)
            }
        }
    }

    private fun saveDataProfile() {
        binding.apply {
            if (checkFormValidation()) {
                viewModelInstance.updateProfileUser(
                    EditProfileRequest(
                        name = etEditName.text.toString(),
                        email = etEditEmail.text.toString(),
                        age = etEditAge.text.toString().toInt(),
                        phoneNumber = etEditPhoneNumber.text.toString()
                    )
                )
            }
        }
    }

    private fun checkFormValidation(): Boolean {
        binding.apply {
            var isValid = true
            when {
                etEditName.text.toString().isEmpty() -> {
                    tfEditName.error = "Fill the name"
                    isValid = false
                }
                etEditEmail.text.toString().isEmpty() -> {
                    tfEditEmail.error = "Fill the email"
                    isValid = false
                }
                etEditAge.text.toString().isEmpty() -> {
                    tfEditAge.error = "Fill the age"
                    isValid = false
                }
                etEditPhoneNumber.text.toString().isEmpty() -> {
                    tfEditPhoneNumber.error = "Fill the phone"
                    isValid = false
                }
                else -> {
                    tfEditName.error = null
                    tfEditEmail.error = null
                    tfEditAge.error = null
                    tfEditPhoneNumber.error = null
                }
            }
            return isValid
        }
    }
}