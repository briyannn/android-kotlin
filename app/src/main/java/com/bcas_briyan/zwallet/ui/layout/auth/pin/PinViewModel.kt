package com.bcas_briyan.zwallet.ui.layout.auth.pin

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bcas_briyan.zwallet.data.ZWalletDataSource
import com.bcas_briyan.zwallet.data.api.ZWalletApi
import com.bcas_briyan.zwallet.model.APIResponse
import com.bcas_briyan.zwallet.model.request.ChangePasswordRequest
import com.bcas_briyan.zwallet.model.request.SetPinRequest
import com.bcas_briyan.zwallet.network.NetworkConfig
import com.bcas_briyan.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {

    fun setPin(request: SetPinRequest): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.setPin(request)
    }

    fun checkPin(pin: String): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.checkPin(pin)
    }

    fun changePassword(request: ChangePasswordRequest): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.changePassword(request)
    }
}