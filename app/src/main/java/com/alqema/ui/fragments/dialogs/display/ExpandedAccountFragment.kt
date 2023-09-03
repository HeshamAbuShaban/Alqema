package com.alqema.ui.fragments.dialogs.display

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alqema.database.local_db.models.Account
import com.alqema.databinding.FragmentExpandedAccountBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExpandedAccountFragment(val account: Account) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentExpandedAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentExpandedAccountBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAccountIntoScreen()
    }

    private fun setupAccountIntoScreen() {
        with(binding) {
            with(account) {
                accountId.text = account.accountNumber.toString()
                binding.accountName.text = accountName
                binding.accountDetails.text = accountDetails
                accountAddress.text = account.address
                accountMobile.text = account.mobileNumber
                accountBelongsTo.text = account.belongsToAccount.toString()
                binding.accountNature.text = account.accountNature
                binding.accountType.text = account.accountType
                binding.accountCurrency.text = account.accountCurrency
                binding.accountBalance.text = account.balance.toString()
            }
        }
    }
}