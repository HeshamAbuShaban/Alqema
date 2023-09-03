package com.alqema.ui.fragments.displaying_ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alqema.adapters.listeners.OnItemClickListener
import com.alqema.adapters.recycler_view.payment.PaymentAdapter
import com.alqema.app_system.node.UseDatabase
import com.alqema.database.local_db.models.Payment
import com.alqema.databinding.FragmentPaymentDisplayBinding
import com.alqema.utils.GeneralUtils
class PaymentDisplayFragment : Fragment(), OnItemClickListener<Payment> {
    private lateinit var binding: FragmentPaymentDisplayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPaymentDisplayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayPayments()
    }

    private fun displayPayments() {
        UseDatabase.getInstance().repository.observePayments().observe(viewLifecycleOwner) {
            setupPaymentAdapter(it)
        }
    }

    private fun setupPaymentAdapter(paymentList: List<Payment>) {
        with(binding.paymentsRecyclerView) {
            setHasFixedSize(true)
            adapter = PaymentAdapter(paymentList).apply {
                registerOnItemClickListener(this@PaymentDisplayFragment)
            }
        }
    }

    // this to handle the click on an item of recycler_view.
    override fun onClick(obj: Payment) {
        /*val navCon = binding.root.findNavController()
        val action =
            PaymentDisplayFragmentDirections.actionNavigationPaymentDisplayToNavigationPaymentItemsDisplay(
                obj.receiptNumber
            )
        //.. REMOVE DEFAULT VALUE OF THE ARG
        navCon.navigate(action)*/
        GeneralUtils.getInstance().showSnackBar(binding.root, obj.details.toString())
    }
}