package com.bcas_briyan.zwallet.model.request

data class GetUserDetailRequest(
    val image: String,
    val balance: Int,
    val id: Int,
    val name: String,
    val phone: String
)

