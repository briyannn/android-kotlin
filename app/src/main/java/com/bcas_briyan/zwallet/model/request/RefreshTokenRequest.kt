package com.bcas_briyan.zwallet.model.request

data class RefreshTokenRequest(
    val email: String,
    val refreshToken: String
)