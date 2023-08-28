package com.alqema.adapters.recycler_view.receipt_items.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.ItemReceiptItemsBinding

class ReceiptItemsViewHolder(private val binding: ItemReceiptItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(category: Category) {
        with(binding) {
            categoryId.text = category.categoryNumber.toString()
            categoryName.text = category.categoryName
            categoryMainUnit.text = category.mainUnit
            categorySellingPrice.text = category.sellingPrice.toString()
            categoryPurchasePrice.text = category.purchasePrice.toString()
        }
    }
}