package com.bcas_briyan.zwallet.ui.layout.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bcas_briyan.zwallet.data.ZWalletDataSource
import com.bcas_briyan.zwallet.model.APIResponse
import com.bcas_briyan.zwallet.model.User
import com.bcas_briyan.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor( private val dataSource: ZWalletDataSource): ViewModel() {

    fun login(email: String, password: String): LiveData<Resource<APIResponse<User>?>> {
        return dataSource.login(email, password)
    }

    fun register(username: String, email: String, password: String): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.register(username, email, password)
    }

    fun setOtp(email: String, otp: String): LiveData<Resource<APIResponse<String>?>> {
        return  dataSource.setOtp(email, otp)
    }
}