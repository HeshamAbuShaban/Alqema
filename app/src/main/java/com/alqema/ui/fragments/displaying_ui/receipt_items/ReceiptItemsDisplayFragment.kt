package com.alqema.ui.fragments.displaying_ui.receipt_items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.alqema.adapters.recycler_view.receipt_items.ReceiptItemsAdapter
import com.alqema.app_system.node.UseDatabase
import com.alqema.database.listeners.Result
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.FragmentReceiptItemsDisplayBinding
import kotlinx.coroutines.launch

class ReceiptItemsDisplayFragment : Fragment() {
    private lateinit var binding: FragmentReceiptItemsDisplayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentReceiptItemsDisplayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        displayCategory()
    }

    private fun setupListeners() {

    }

    private fun displayCategory() {
        val args: ReceiptItemsDisplayFragmentArgs by navArgs()
        lifecycleScope.launch {
            when (val result =
                UseDatabase.getInstance().repository
                    .fetchCategoriesOfReceipt(args.receiptId)) {
                is Result.Success<List<Category>> -> {
                    val categories = result.data
                    // Process categories here
                    UseDatabase.getInstance().repository.getCustomerBasedOnReceiptId(
                        this,
                        args.receiptId
                    ) {
                        with(binding) {
                            customerName.text = it?.accountName ?: "Error404"
                            itemsCount.text = categories.size.toString()
                        }
                    }

                    setupCategoryAdapter(categories)
                }

                is Result.Error -> {
                    val exception = result.exception
                    // Handle the error
                    binding.itemsCount.text = exception.toString()
                }
            }
        }

    }

    private fun setupCategoryAdapter(categoryList: List<Category>) {
        with(binding.categoriesRecyclerView) {
            setHasFixedSize(true)
            adapter = ReceiptItemsAdapter(categoryList)
        }
    }
}