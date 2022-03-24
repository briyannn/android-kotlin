package com.bcas_briyan.zwallet.data

import androidx.lifecycle.liveData
import com.bcas_briyan.zwallet.data.api.ZWalletApi
import com.bcas_briyan.zwallet.model.request.*
import com.bcas_briyan.zwallet.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

class ZWalletDataSource @Inject constructor(private val apiClient: ZWalletApi) {
    fun login(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val loginRequest = LoginRequest(email = email, password = password)
            val response = apiClient.login(loginRequest)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun getInvoice() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = apiClient.getInvoice()
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun getBalance() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = apiClient.getBalance()
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun getMyProfile() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = apiClient.getMyProfile()
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun setPin(request: SetPinRequest) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = apiClient.setPin(request)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun getAllContacts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            var response = apiClient.getAllContacts()
            emit(Resource.success(response))
        } catch (e: Exception){
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun transfer(data: TransferRequest, pin: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val resource = apiClient.transfer(data, pin)
            emit(Resource.success(resource))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun register(username: String, email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val registerRequest = RegisterRequest(username = username, email = email, password = password)
            val response = apiClient.register(registerRequest)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun setOtp(email: String, otp: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = apiClient.setOtp(email, otp)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun checkPin(pin: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val resource = apiClient.checkPin(pin = pin)
            emit(Resource.success(resource))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun changePassword(request: ChangePasswordRequest) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = apiClient.changePassword(request)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }

    fun topUpBalance(request: TopUpBalanceRequest) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = apiClient.topUpBalance(request)
            emit(Resource.success(response))
        } catch (e: Exception) {
            emit(Resource.error(null, e.localizedMessage))
        }
    }
}