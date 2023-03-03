package com.pascal.movieku.presentation.ui.homepage.profile.user

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.pascal.movieku.R
import com.pascal.movieku.base.arch.BaseFragment
import com.pascal.movieku.base.model.Resource
import com.pascal.movieku.data.local.model.entity.UserEntity
import com.pascal.movieku.databinding.FragmentProfileUserBinding
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ProfileUserFragment : BaseFragment<FragmentProfileUserBinding, ProfileUserViewModel>(
    FragmentProfileUserBinding::inflate
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
            ivBackHome.setOnClickListener {
                moveNav(R.id.action_profileUserFragment_to_homeFragment)
            }

            cvProfile.setOnClickListener {
                openImagePicker()
            }

            btnEdit.setOnClickListener {
                moveNav(R.id.action_profileUserFragment_to_editProfileFragment)
            }

            btnLogout.setOnClickListener {
                viewModelInstance.logoutUser()
            }
        }
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModelInstance.getUserResult.collect {
                    if (it is Resource.Success) {
                        setProfileUser(it.data)
                    }
                }
            }

            launchWhenStarted {
                viewModelInstance.apply {
                    updateProfileImageResult.collect {
                        if (it is Resource.Success) {
                            getUser()
                        }
                    }
                }
            }

            launchWhenStarted {
                viewModelInstance.logoutUserResult.collect {
                    if (it is Resource.Success) {
                        moveNav(R.id.action_profileUserFragment_to_loginFragment)
                    }
                }
            }
        }
    }

    private fun setProfileUser(data: UserEntity?) {
        binding.apply {
            data?.let {
                tvGetUsername.text = it.username
                Glide.with(root)
                    .load(it.imageProfile)
                    .into(ivProfile)
                tvGetName.text = it.name
                tvGetEmail.text = it.email
                tvGetAge.text = it.age.toString()
                tvGetPhone.text = it.phoneNumber
            }
        }
    }

    private fun openImagePicker() {
        ImagePicker.with(this)
            .crop()
            .saveDir(File(activity?.externalCacheDir, "Image Picker"))
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data
                    fileUri?.let { saveImage(it) }
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    private fun saveImage(uri: Uri) {
        binding.apply {
            viewModelInstance.updateProfileImage(uri.toString())
        }
    }
}