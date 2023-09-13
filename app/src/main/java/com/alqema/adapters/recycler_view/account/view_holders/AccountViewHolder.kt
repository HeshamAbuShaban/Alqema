package com.alqema.adapters.recycler_view.account.view_holders

import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.alqema.database.local_db.models.Account
import com.alqema.databinding.ItemAccountBinding

class AccountViewHolder(private val binding: ItemAccountBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(account: Account) {
        with(binding) {
            accountId.text = account.accountNumber.toString()
            accountName.text = account.accountName
            accountBalance.text = account.balance.toFloat().toString()
        }
    }

    fun getExpandButton(): ImageButton = binding.btnExpand

}