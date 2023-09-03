package com.alqema.adapters.recycler_view.payment.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.alqema.database.local_db.models.Payment
import com.alqema.databinding.ItemPaymentBinding
import java.text.SimpleDateFormat
import java.util.Locale

class PaymentViewHolder(private val binding: ItemPaymentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(payment: Payment) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        with(binding) {
            paymentId.text = payment.id.toString()
            paymentCustomerNumber.text = payment.accountID.toString()
            paymentDetails.text = payment.details
            paymentDate.text = dateFormat.format(payment.date)
            paymentAmount.text = payment.payment.toString()
        }
    }
}