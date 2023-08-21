package com.alqema.adapters.recycler_view.account.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.alqema.databinding.ItemAccountBinding
import com.alqema.database.local_db.models.Account

class AccountViewHolder(private val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(account: Account) {
        with(binding){
            accountId.text = account.accountNumber.toString()
            accountName.text = account.accountName
            accountAddress.text = account.address
            accountMobile.text = account.mobileNumber
        }
    }
}