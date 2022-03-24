package com.bcas_briyan.zwallet.model.request

import com.google.gson.annotations.SerializedName

data class SetPinRequest(
    @SerializedName("PIN")
    val pin: String
)
