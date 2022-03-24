package com.bcas_briyan.zwallet.model.request

import com.google.gson.annotations.SerializedName

data class AllContactRequest(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?
)
