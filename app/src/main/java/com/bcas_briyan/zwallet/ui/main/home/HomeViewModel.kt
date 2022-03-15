package com.bcas_briyan.zwallet.ui.main.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bcas_briyan.zwallet.data.ZWalletDataSource
import com.bcas_briyan.zwallet.data.api.ZWalletApi
import com.bcas_briyan.zwallet.model.APIResponse
import com.bcas_briyan.zwallet.model.Invoice
import com.bcas_briyan.zwallet.model.PersonalProfile
import com.bcas_briyan.zwallet.model.UserDetail
import com.bcas_briyan.zwallet.network.NetworkConfig
import com.bcas_briyan.zwallet.utils.Resource

class HomeViewModel(app: Application): ViewModel() {
    private var apiClient: ZWalletApi = NetworkConfig(app).buildApi()
    private var dataSource = ZWalletDataSource(apiClient)

    fun getInvoice(): LiveData<Resource<APIResponse<List<Invoice>>?>> {
        return dataSource.getInvoice()
    }

    fun getBalance(): LiveData<Resource<APIResponse<List<UserDetail>>?>> {
        return dataSource.getBalance()
    }

    fun getMyProfile(): LiveData<Resource<APIResponse<PersonalProfile>?>> {
        return dataSource.getMyProfile()
    }
}