package com.alqema.ui.fragments.creation_ui.account

import androidx.lifecycle.ViewModel
import com.alqema.app_system.AppController
import com.alqema.database.repo.AlqemaRepository
import com.alqema.databinding.FragmentAddAccountBinding
import com.alqema.models.Account
import com.alqema.models.constants.account.AccountCurrency
import com.alqema.models.constants.account.AccountDetails
import com.alqema.models.constants.account.AccountNature
import com.alqema.models.constants.account.AccountType

class AccountViewModel : ViewModel() {

    private lateinit var _binding: FragmentAddAccountBinding
    private val binding get() = _binding
    private val repo: AlqemaRepository = AlqemaRepository(AppController.getInstance())

    private lateinit var accountIdVH: Number
    private lateinit var accountNameVH: String
    private lateinit var accountDetailsVH: AccountDetails

    // --
    private lateinit var addressVH: String
    private lateinit var mobileVH: String
    private lateinit var accountBelongsToVH: Number

    // --
    private lateinit var accountNatureVH: AccountNature
    private lateinit var accountTypeVH: AccountType
    private lateinit var accountCurrencyVH: AccountCurrency

    //..
    private fun readSpinnerData() {
        with(binding) {
            with(spAccountDetails) {
                when (selectedItemPosition) {
                    0 -> accountDetailsVH = AccountDetails.Dealers
                    1 -> accountDetailsVH = AccountDetails.Customer
                    2 -> accountDetailsVH = AccountDetails.Expenses
                    3 -> accountDetailsVH = AccountDetails.Employees
                }
            }
            with(spAccountNature) {
                when (selectedItemPosition) {
                    0 -> accountNatureVH = AccountNature.DebtorOnly
                    1 -> accountNatureVH = AccountNature.CreditorOnly
                    2 -> accountNatureVH = AccountNature.DebtorAndCreditor
                }
            }
            with(spAccountType) {
                when (selectedItemPosition) {
                    0 -> accountTypeVH = AccountType.BalanceSheet
                    1 -> accountTypeVH = AccountType.ProfitAndLoss
                    2 -> accountTypeVH = AccountType.Trade
                    3 -> accountTypeVH = AccountType.Employment
                    4 -> accountTypeVH = AccountType.RevenuesAndExpenses
                    5 -> accountTypeVH = AccountType.ReceivablesOrCreditors
                    6 -> accountTypeVH = AccountType.AgentsAndDistributors
                    7 -> accountTypeVH = AccountType.Project
                }
            }
            with(spAccountCurrency) {
                when (selectedItemPosition) {
                    0 -> accountCurrencyVH = AccountCurrency.NIS
                    1 -> accountCurrencyVH = AccountCurrency.USD
                    2 -> accountCurrencyVH = AccountCurrency.JOD
                    3 -> accountCurrencyVH = AccountCurrency.EGP
                    4 -> accountCurrencyVH = AccountCurrency.EUR
                    5 -> accountCurrencyVH = AccountCurrency.GBP
                    6 -> accountCurrencyVH = AccountCurrency.AUD
                    7 -> accountCurrencyVH = AccountCurrency.CAD
                    8 -> accountCurrencyVH = AccountCurrency.CHF
                    9 -> accountCurrencyVH = AccountCurrency.CNY
                    10 -> accountCurrencyVH = AccountCurrency.JPY
                    11 -> accountCurrencyVH = AccountCurrency.AED
                    12 -> accountCurrencyVH = AccountCurrency.SAR
                    13 -> accountCurrencyVH = AccountCurrency.QAR
                    14 -> accountCurrencyVH = AccountCurrency.KWD
                    15 -> accountCurrencyVH = AccountCurrency.BHD
                    16 -> accountCurrencyVH = AccountCurrency.OMR
                    17 -> accountCurrencyVH = AccountCurrency.LBP
                    18 -> accountCurrencyVH = AccountCurrency.TRY
                    19 -> accountCurrencyVH = AccountCurrency.INR
                    20 -> accountCurrencyVH = AccountCurrency.ZAR
                    21 -> accountCurrencyVH = AccountCurrency.SGD
                    22 -> accountCurrencyVH = AccountCurrency.NZD
                }
            }
        }
    }

    private fun readInputsText() {
        with(binding) {
            accountIdVH = edAccountId.text.toString().toInt()
            accountNameVH = edAccountName.text.toString()
            addressVH = edAccountAddress.text.toString()
            mobileVH = edAccountMobileNumber.text.toString()
            accountNameVH = edAccountName.text.toString()
            accountBelongsToVH = edAccountBelongsAccount.text.toString().toInt()
        }
    }

    //..

    //..

    fun performCreation(binding: FragmentAddAccountBinding, function: () -> Unit) {
        this._binding = binding
        collectUserInputs()
        if (checkData()) create() else function()
    }

    private fun collectUserInputs() {
        readSpinnerData()
        readInputsText()
    }

    private fun checkData(): Boolean =
        accountIdVH.toString().isNotEmpty()
                && accountNameVH.isNotEmpty()
                && mobileVH.isNotEmpty()
                && addressVH.isNotEmpty()
                && accountDetailsVH.toString().isNotEmpty()
                && accountTypeVH.toString().isNotEmpty()
                && accountNatureVH.toString().isNotEmpty()
                && accountCurrencyVH.toString().isNotEmpty()
                && accountBelongsToVH.toString().isNotEmpty()

    private fun create() {
        // Create the instance
        val accountInstance = Account.Builder()
            .withAccountNumber(accountIdVH.toInt())
            .withAccountName(accountNameVH)
            .withMobileNumber(mobileVH)
            .withAddress(addressVH)
            .withAccountDetails(accountDetailsVH)
            .withAccountType(accountTypeVH)
            .withAccountNature(accountNatureVH)
            .withAccountCurrency(accountCurrencyVH)
            .withBelongsToAccount(accountBelongsToVH.toInt())
            .build()
        // Use the instance
        repo.insertAccount(accountInstance)
        // The insert is done now empty the screen inputs for new data
        clearInputs()
    }

    private fun clearInputs() {
        with(binding) {
            edAccountId.setText("")
            edAccountName.setText("")
            edAccountAddress.setText("")
            edAccountMobileNumber.setText("")
            edAccountName.setText("")
            edAccountBelongsAccount.setText("")
        }
    }

    override fun onCleared() {
        super.onCleared()
        repo.tearDown()
    }

}