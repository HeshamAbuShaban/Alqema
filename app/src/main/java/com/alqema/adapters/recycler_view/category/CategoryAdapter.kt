package com.alqema.adapters.recycler_view.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqema.adapters.listeners.CategoryContract
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.listeners.OnItemExpandClickListener
import com.alqema.adapters.recycler_view.category.view_holders.CategoryViewHolder
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.ItemCategoryBinding

class CategoryAdapter(private val categoryList: List<Category>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private var listener: OnItemClickListener<CategoryContract>? = null

    private var expandItemClickListener: OnItemExpandClickListener<Category>? = null

    fun registerOnItemClickListener(listener: OnItemClickListener<CategoryContract>) {
        this.listener = listener
    }

    fun registerOnExpandItemClickListener(listener: OnItemExpandClickListener<Category>) {
        this.expandItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
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

        holder.itemView.setOnLongClickListener {
            listener?.onClick(category)
            false
        }

        holder.getExpandButton().setOnClickListener {
            expandItemClickListener?.onExpandClick(category)
        }
    }
}