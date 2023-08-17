package com.alqema.adapters.recycler_view.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqema.adapters.recycler_view.account.view_holders.AccountViewHolder
import com.alqema.databinding.ItemAccountBinding
import com.alqema.models.Account

class AccountAdapter(private val accountList: List<Account>) :
    RecyclerView.Adapter<AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            ItemAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = accountList.size

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = accountList[position]
        holder.bindData(account)
    }
}