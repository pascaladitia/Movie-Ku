package com.pascal.movieku.data.local.model.request

data class EditProfileRequest(
    val name: String,
    val email: String,
    val age: Int,
    val phoneNumber: String
)