package com.bcas_briyan.zwallet.model.request

data class GetInvoiceRequest(
    val amount: Int,
    val id: Int,
    val name: String,
    val image: String,
    val createdAt: String,
    val sender: Int,
    val reciever: Int
)
