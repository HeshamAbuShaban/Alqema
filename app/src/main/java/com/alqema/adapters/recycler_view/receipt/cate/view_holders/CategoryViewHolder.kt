package com.alqema.adapters.recycler_view.receipt.cate.view_holders

import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.ItemMiniCategoryBinding

class CategoryViewHolder(private val binding: ItemMiniCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(category: Category) {
        with(binding) {
            categoryId.text = category.categoryNumber.toString()
            categoryName.text = category.categoryName
            categorySellingPrice.text = category.sellingPrice.toString()
        }
    }

    fun getRemoveButton(): ImageButton {
        return binding.btnRemoveItem
    }
}