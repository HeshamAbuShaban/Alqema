package com.alqema.adapters.recycler_view.receipt.acc.view_holders

import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.alqema.database.local_db.models.Account
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.ItemMiniAccountBinding
import com.alqema.databinding.ItemMiniCategoryBinding

class AccountViewHolder(private val binding: ItemMiniAccountBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(account: Account) {
        with(binding) {
            categoryId.text = account.accountNumber.toString()
            categoryName.text = account.accountName
        }
    }

    fun getRemoveButton(): ImageButton {
        return binding.btnRemoveItem
    }

}