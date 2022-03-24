package com.bcas_briyan.zwallet.network

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.bcas_briyan.zwallet.data.api.ZWalletApi
import com.bcas_briyan.zwallet.utils.BASE_URL
import com.bcas_briyan.zwallet.utils.KEY_USER_TOKEN
import com.bcas_briyan.zwallet.utils.PREFS_NAME
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkConfig @Inject constructor(var pref: SharedPreferences) {

    private fun getInterceptor(authenticator: Authenticator? = null): OkHttpClient {
        /*val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val token = preferences?.getString(KEY_USER_TOKEN, "")
        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)*/
        var token = pref.getString(KEY_USER_TOKEN,"")
        Log.d("token", token!!)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(logging)

        if (!token.isNullOrEmpty()) {
            client.addInterceptor(TokenInterceptor { token })
//                preferences?.getString(KEY_USER_TOKEN, "").toString()
            }

        if (authenticator != null) {
            client.authenticator(authenticator)
        }

        return client.build()
    }

    private fun getService(): ZWalletApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getInterceptor())
            .build().create(ZWalletApi::class.java)
    }

    /*fun buildApi(): ZWalletApi {
        val authenticator = RefreshTokenInterceptor(context, getService(), preferences!!)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptor(authenticator))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZWalletApi::class.java)
    }*/

    fun buildApi():ZWalletApi {
        val authenticator = RefreshTokenInterceptor( getService(), pref)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptor(authenticator))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZWalletApi::class.java)
    }
}