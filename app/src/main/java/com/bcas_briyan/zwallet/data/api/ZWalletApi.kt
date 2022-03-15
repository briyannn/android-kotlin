package com.bcas_briyan.zwallet.data.api

import com.bcas_briyan.zwallet.model.*
import com.bcas_briyan.zwallet.model.request.LoginRequest
import com.bcas_briyan.zwallet.model.request.RefreshTokenRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ZWalletApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): APIResponse<User>

    @GET("user/myProfile")
    suspend fun getMyProfile(): APIResponse<PersonalProfile>

    @GET("home/getBalance")
    suspend fun getBalance(): APIResponse<List<UserDetail>>

    @GET("home/getInvoice")
    suspend fun getInvoice(): APIResponse<List<Invoice>>

    @POST("auth/refresh-token")
    fun refreshToken(@Body request: RefreshTokenRequest): Call<APIResponse<User>>

//    @POST("auth/signup")
//    fun register(@Body request: RegisterRequest): Call<ApiResponse<String>>

}