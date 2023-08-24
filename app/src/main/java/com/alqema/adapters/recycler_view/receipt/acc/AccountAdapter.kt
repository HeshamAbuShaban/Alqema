package com.alqema.adapters.recycler_view.receipt.acc

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqema.adapters.listeners.OnItemActionClickListener
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.receipt.acc.view_holders.AccountViewHolder
import com.alqema.database.local_db.models.Account
import com.alqema.databinding.ItemMiniAccountBinding

class AccountAdapter(private val accountList: ArrayList<Account>) :
    RecyclerView.Adapter<AccountViewHolder>() {

    private var listener: OnItemClickListener<Account>? = null
    private var actionListener: OnItemActionClickListener<Account>? = null
    private var visibility: Int = View.GONE

    fun setVisibility(visibility: Boolean) {
        this.visibility = if (visibility) View.VISIBLE else View.GONE
    }


    /*fun getVisibility(): Int {
        return visibility
    }*/

    fun registerOnItemClickListener(listener: OnItemClickListener<Account>) {
        this.listener = listener
    }

    fun registerOnItemActionClickListener(listener: OnItemActionClickListener<Account>) {
        this.actionListener = listener
    }

    fun addUpAccountList(accountList: List<Account>) {

        if (this.accountList.isNotEmpty()) this.accountList.clear()

        this.accountList.addAll(accountList)
        notifyItemRangeChanged(0, accountList.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeFromAccountList(accountList: List<Account>) {

        if (this.accountList.isNotEmpty()) this.accountList.clear()

        this.accountList.addAll(accountList) // TODO:Check This Out.
        notifyDataSetChanged()
    }

    /*fun addSingleAccount(account: Account) {
        this.accountList.add(account) // TO-DONE:Check This Out.
        notifyItemRangeInserted(0, accountList.size)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            ItemMiniAccountBinding.inflate(
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

        holder.itemView.setOnClickListener {
            listener?.onClick(account) ?: Log.d("ReceiptAccountAdapter", "Listener Is Null")
        }
        if (visibility == View.VISIBLE) {
            setupRemoveBtn(holder, account)
        }

    }

    // TODO:Remove This
    private fun setupRemoveBtn(holder: AccountViewHolder, account: Account) {
        with(holder) {
            getRemoveButton().visibility = this@AccountAdapter.visibility
            getRemoveButton().setOnClickListener {
                actionListener?.onDelete(account)
            }
        }
    }
}