package com.alqema.adapters.recycler_view.receipt.cate

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.receipt.cate.view_holders.CategoryViewHolder
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.ItemMiniCategoryBinding

class CategoryAdapter(private val categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private var listener: OnItemClickListener<Category>? = null

    fun registerOnItemClickListener(listener: OnItemClickListener<Category>) {
        this.listener = listener
    }

    fun addUpCategoryList(categoryList: List<Category>) {

        if (this.categoryList.isNotEmpty()) this.categoryList.clear()

        this.categoryList.addAll(categoryList) // TODO:Check This Out.
        notifyItemRangeChanged(0, categoryList.size)
    }

    fun addSingleCategory(category: Category) {
        this.categoryList.add(category) // TODO:Check This Out.
        notifyItemRangeInserted(0, categoryList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemMiniCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bindData(category)

        holder.itemView.setOnClickListener {
            listener?.onClick(category) ?: Log.d("ReceiptCategoryAdapter","Listener Is Null")
        }

    }
}