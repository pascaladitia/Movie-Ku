package com.pascal.movieku.data.local.model.request

data class RegisterRequest(
    val name: String?,
    val imageProfile: String?,
    val email: String?,
    val age: Int?,
    val phoneNumber: String?,
    val username: String?,
    val password: String?
)