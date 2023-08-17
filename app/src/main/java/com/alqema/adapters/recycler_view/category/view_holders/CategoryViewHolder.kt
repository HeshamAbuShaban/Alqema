package com.alqema.adapters.recycler_view.category.view_holders

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alqema.R
import com.alqema.databinding.ItemCategoryBinding
import com.alqema.models.Category

class CategoryViewHolder(itemView: ItemCategoryBinding) : RecyclerView.ViewHolder(itemView.root) {
    private lateinit var textId: TextView
    private lateinit var textName: TextView
    private lateinit var textDetails: TextView
    private lateinit var textBelongsTo: TextView

    init {
        findViews()
    }

    private fun findViews() {
        textId = itemView.findViewById(R.id.accountId)
        textName = itemView.findViewById(R.id.accountName)
        textDetails = itemView.findViewById(R.id.accountDetails)
        textBelongsTo = itemView.findViewById(R.id.accountBelongsTo)
    }

    fun bindData(category: Category) {
        textId.text = category.categoryNumber.toString()
        textName.text = category.categoryName
        textDetails.text = category.mainUnit
        textBelongsTo.text = category.unitBarcode.toString()
    }
}