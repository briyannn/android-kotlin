package com.bcas_briyan.zwallet.data.api

import com.bcas_briyan.zwallet.model.*
import com.bcas_briyan.zwallet.model.request.*
import retrofit2.Call
import retrofit2.http.*

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

    @PATCH("auth/PIN")
    suspend fun setPin(@Body request: SetPinRequest): APIResponse<String>

    @POST("auth/signup")
    suspend fun register(@Body request: RegisterRequest): APIResponse<String>

    @GET("tranfer/contactUser")
    suspend fun getAllContacts(): APIResponse<List<AllContactRequest>>

    @GET("auth/checkPIN/{PIN}")
    suspend fun checkPin(@Path("PIN") pin: String): APIResponse<String>

    @POST("tranfer/newTranfer")
    suspend fun transfer(
        @Body request: TransferRequest,
        @Header("x-access-PIN") pin: String
    ): APIResponse<Transfer>

    @PATCH("user/changePassword")
    suspend fun changePassword(@Body request: ChangePasswordRequest): APIResponse<String>

    @PATCH("topup/topupbalance")
    suspend fun topUpBalance(@Body request: TopUpBalanceRequest): APIResponse<String>

    @GET("auth/activate/{email}/{otp}")
    suspend fun setOtp(@Path("email") email: String, @Path("otp") otp: String): APIResponse<String>
}