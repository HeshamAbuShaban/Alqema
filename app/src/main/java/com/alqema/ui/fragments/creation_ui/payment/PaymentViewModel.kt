package com.alqema.ui.fragments.creation_ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqema.R
import com.alqema.app_system.AppController
import com.alqema.app_system.node.UseDatabase
import com.alqema.database.local_db.models.Account
import com.alqema.database.local_db.models.Payment
import com.alqema.databinding.FragmentAddPaymentBinding
import com.alqema.utils.GeneralUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    private val context = AppController.getInstance()
    private lateinit var binding: FragmentAddPaymentBinding
    private var time: Long? = null

    //.. Holders
    private var accountID: Int = 1
    private var payment: Double = 1.0
    private var details: String = ""
    private var date: Long = 1

    fun performCreation(binding: FragmentAddPaymentBinding, pickedAccount: Account) {
        this.binding = binding
        this.time = System.currentTimeMillis()
        readInputs(pickedAccount)
        if (checkData()) create() else showError()
    }

    private fun checkData(): Boolean =
        accountID != 0 && payment > 0.0 && date == time

    private fun readInputs(pickedAccount: Account) {
        with(binding) {
            accountID = pickedAccount.accountNumber
            payment = edPaymentAmount.text.toString().toDouble()
            details = edPaymentDetails.text.toString()
            date = time!!
        }
    }

    private fun create() {
        val paymentObj = Payment.Builder()
            .withAccountID(accountID)
            .withDetails(details)
            .withPayment(payment)
            .withDate(date)
            .build()

        viewModelScope.launch(Dispatchers.IO) {
            UseDatabase.getInstance().repository.insertPayment(paymentObj)
            UseDatabase.getInstance().repository.updateAccountBalanceForPayment(accountID, payment)
        }

        GeneralUtils.getInstance()
            .showSnackBar(binding.root, context.getString(R.string.created_message))
    }

    private fun showError() {
        GeneralUtils
            .getInstance()
            .showSnackBar(binding.root, context.getString(R.string.empty_fields_message))
    }

}