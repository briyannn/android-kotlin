package com.bcas_briyan.zwallet.di

import android.content.Context
import android.content.SharedPreferences
import com.bcas_briyan.zwallet.data.ZWalletDataSource
import com.bcas_briyan.zwallet.data.api.ZWalletApi
import com.bcas_briyan.zwallet.network.NetworkConfig
import com.bcas_briyan.zwallet.utils.PREFS_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context =context

    @Provides
    @Singleton
    fun getSharedPreferences(context: Context) = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAPI(pref: SharedPreferences): ZWalletApi = NetworkConfig(pref).buildApi()

    @Provides
    @Singleton
    fun provideDataSource(apiClient: ZWalletApi): ZWalletDataSource = ZWalletDataSource(apiClient)
}