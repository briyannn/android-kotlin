package com.bcas_briyan.zwallet.model.request

import com.google.gson.annotations.SerializedName

data class TopUpBalanceRequest(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("amount")
    val amount: String
)

