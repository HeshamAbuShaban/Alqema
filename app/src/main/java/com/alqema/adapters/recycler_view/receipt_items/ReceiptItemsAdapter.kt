package com.alqema.adapters.recycler_view.receipt_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqema.adapters.listeners.CategoryContract
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.receipt_items.view_holders.ReceiptItemsViewHolder
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.ItemReceiptItemsBinding

class ReceiptItemsAdapter(private val categoryList: List<Category>) :
    RecyclerView.Adapter<ReceiptItemsViewHolder>() {

    private var listener: OnItemClickListener<CategoryContract>? = null

    fun registerOnItemClickListener(listener: OnItemClickListener<CategoryContract>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptItemsViewHolder {
        return ReceiptItemsViewHolder(
            ItemReceiptItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: ReceiptItemsViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bindData(category)

        holder.itemView.setOnLongClickListener {
            listener?.onClick(category)
            false
        }
    }
}