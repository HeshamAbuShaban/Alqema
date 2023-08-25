package com.alqema.adapters.recycler_view.receipt.self

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.receipt.self.view_holders.ReceiptViewHolder
import com.alqema.databinding.ItemReceiptBinding
import com.alqema.database.local_db.models.Receipt

class ReceiptAdapter(private val receiptList: List<Receipt>) :
    RecyclerView.Adapter<ReceiptViewHolder>() {

    private var listener: OnItemClickListener<Receipt>? = null
    fun registerOnItemClickListener(listener: OnItemClickListener<Receipt>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        return ReceiptViewHolder(
            ItemReceiptBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = receiptList.size

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val receipt = receiptList[position]
        holder.bindData(receipt)

        holder.itemView.setOnLongClickListener {
            listener?.onClick(receipt)
            false
        }

    }
}