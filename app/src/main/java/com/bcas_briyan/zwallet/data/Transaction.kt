package com.bcas_briyan.zwallet.data

import android.graphics.drawable.Drawable

data class Transaction(
    val transactionImage: Drawable,
    val transactionName: String,
    val transactionType: String,
    val transactionNominal: Double
)
