package com.alqema.adapters.recycler_view.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqema.adapters.listeners.AccountContract
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.account.view_holders.AccountViewHolder
import com.alqema.databinding.ItemAccountBinding
import com.alqema.database.local_db.models.Account

class AccountAdapter(private val accountList: List<Account>) :
    RecyclerView.Adapter<AccountViewHolder>() {

    private var listener: OnItemClickListener<AccountContract>? = null
    fun registerOnItemClickListener(listener: OnItemClickListener<AccountContract>) {
        this.listener = listener
    }

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

        holder.itemView.setOnLongClickListener {
            listener?.onClick(account)
            false
        }

    }
}