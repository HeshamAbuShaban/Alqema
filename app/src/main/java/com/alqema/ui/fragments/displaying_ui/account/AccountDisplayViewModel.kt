package com.alqema.ui.fragments.displaying_ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alqema.app_system.AppController
import com.alqema.database.repo.AlqemaRepository
import com.alqema.database.local_db.models.Account

class AccountDisplayViewModel : ViewModel() {

    private val _accountList = MutableLiveData<List<Account>>()
    val accountList: LiveData<List<Account>> = _accountList

    fun accountList(accountList: LiveData<List<Account>>) {
        this._accountList.value = accountList.value
    }

    fun fetchData() {
        val repo = AlqemaRepository(AppController.getInstance())
        accountList(repo.observeAccounts())
    }

}