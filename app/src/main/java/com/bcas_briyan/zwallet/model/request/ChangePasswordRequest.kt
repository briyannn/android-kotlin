package com.bcas_briyan.zwallet.model.request

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("old_password")
    val old_password: String,
    @SerializedName("new_password")
    val new_password: String
)
