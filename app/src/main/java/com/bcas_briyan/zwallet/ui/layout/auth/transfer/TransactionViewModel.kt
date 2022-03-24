package com.bcas_briyan.zwallet.ui.layout.auth.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bcas_briyan.zwallet.data.ZWalletDataSource
import com.bcas_briyan.zwallet.model.APIResponse
import com.bcas_briyan.zwallet.model.Transfer
import com.bcas_briyan.zwallet.model.UserDetail
import com.bcas_briyan.zwallet.model.request.AllContactRequest
import com.bcas_briyan.zwallet.model.request.TopUpBalanceRequest
import com.bcas_briyan.zwallet.model.request.TransferRequest
import com.bcas_briyan.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {
    private var selectedContact = MutableLiveData<AllContactRequest>()
    private var transfer = MutableLiveData<TransferRequest>()

    fun getBalance(): LiveData<Resource<APIResponse<List<UserDetail>>?>> {
        return dataSource.getBalance()
    }

    fun getContact(): LiveData<com.bcas_briyan.zwallet.utils.Resource<APIResponse<List<AllContactRequest>>?>> {
        return dataSource.getAllContacts()
    }

    fun setSelectedContact(contact: AllContactRequest) {
        selectedContact.value = contact
    }

    fun selectedContact(): MutableLiveData<AllContactRequest> {
        return selectedContact
    }

    fun getTransferParam(): MutableLiveData<TransferRequest> {
        return transfer
    }

    fun setTransferParam(data: TransferRequest) {
        transfer.value = data
    }

    fun transfer(data: TransferRequest, pin: String): LiveData<Resource<APIResponse<Transfer>?>> {
        return dataSource.transfer(data, pin)
    }

    fun topUpBalance(data: TopUpBalanceRequest): LiveData<Resource<APIResponse<String>?>> {
        return dataSource.topUpBalance(data)
    }
}