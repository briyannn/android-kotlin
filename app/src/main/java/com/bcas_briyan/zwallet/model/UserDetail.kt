package com.bcas_briyan.zwallet.model

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("email")
    val email: String?,
    @SerializedName("lastname")
    val lastName: String?,
    @SerializedName("firstname")
    val firstName: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("balance")
    val balance: Int?,
    @SerializedName("phone")
    val phone: String?
)
