package com.alqema.adapters.recycler_view.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.payment.view_holders.PaymentViewHolder
import com.alqema.databinding.ItemPaymentBinding
import com.alqema.database.local_db.models.Payment

class PaymentAdapter(private val paymentList: List<Payment>) :
    RecyclerView.Adapter<PaymentViewHolder>() {

    private var listener: OnItemClickListener<Payment>? = null
    fun registerOnItemClickListener(listener: OnItemClickListener<Payment>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        return PaymentViewHolder(
            ItemPaymentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = paymentList.size

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = paymentList[position]
        holder.bindData(payment)

        holder.itemView.setOnLongClickListener {
            listener?.onClick(payment)
            false
        }

    }
}