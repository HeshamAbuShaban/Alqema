package com.alqema.adapters.recycler_view.category.view_holders

import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.alqema.databinding.ItemCategoryBinding
import com.alqema.database.local_db.models.Category

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(category: Category) {
        with(binding) {
            categoryId.text = category.categoryNumber.toString()
            categoryName.text = category.categoryName
            categoryMainUnit.text = category.mainUnit
            categorySellingPrice.text = category.sellingPrice.toString()
        }
    }

    fun getExpandButton(): ImageButton = binding.btnExpand
}