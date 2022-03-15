package com.bcas_briyan.zwallet.model

//status code :
//200 success
//401 error/failed
data class APIResponse<T>(
    var status: Int,
    var message: String,
    var data: T?
)

