package com.alqema.adapters.recycler_view.receipt.self.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.alqema.database.local_db.models.Receipt
import com.alqema.databinding.ItemReceiptBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ReceiptViewHolder(private val binding: ItemReceiptBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(receipt: Receipt) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        with(binding) {
            receiptId.text = receipt.receiptNumber.toString()
            receiptCustomerNumber.text = receipt.accountNumber.toString()
            receiptDetails.text = receipt.receiptDetails
            receiptDate.text = dateFormat.format(receipt.receiptDate)
            receiptTotal.text = receipt.total.toString()
        }
    }
}