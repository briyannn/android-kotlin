package com.bcas_briyan.zwallet.ui.layout.auth.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bcas_briyan.zwallet.data.ZWalletDataSource
import com.bcas_briyan.zwallet.model.APIResponse
import com.bcas_briyan.zwallet.model.request.AllContactRequest
import com.bcas_briyan.zwallet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val dataSource: ZWalletDataSource): ViewModel() {
    private val selectedContact = MutableLiveData<AllContactRequest>()

    fun getAllContacts() : LiveData<Resource<APIResponse<List<AllContactRequest>>?>> {
        return dataSource.getAllContacts()
    }

    fun setSelectedContact(contact: AllContactRequest){
        selectedContact.value = contact
    }

    fun getSelectedContact(): MutableLiveData<AllContactRequest>{
        return selectedContact
    }

}