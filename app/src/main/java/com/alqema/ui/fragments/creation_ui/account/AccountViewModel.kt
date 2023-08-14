package com.alqema.ui.fragments.creation_ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alqema.models.Account

class AccountViewModel : ViewModel() {
    var accountList = MutableLiveData<List<Account>>()
        private set

    fun accountList(accountList: List<Account>) {
        this.accountList.value = accountList
    }

}