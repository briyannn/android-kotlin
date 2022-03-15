package com.bcas_briyan.zwallet.model.request

data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String
)