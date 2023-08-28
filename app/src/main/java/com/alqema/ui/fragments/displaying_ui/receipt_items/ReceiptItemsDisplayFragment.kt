package com.alqema.ui.fragments.displaying_ui.receipt_items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.alqema.adapters.recycler_view.receipt_items.ReceiptItemsAdapter
import com.alqema.app_system.AppController
import com.alqema.database.local_db.models.Category
import com.alqema.database.repo.AlqemaRepository
import com.alqema.databinding.FragmentReceiptItemsDisplayBinding

class ReceiptItemsDisplayFragment : Fragment(){
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
        Log.d("AddAccountFragment", "onViewCreated() returned: ${args.receiptId}")

        val repo = AlqemaRepository(AppController.getInstance())
        repo.fetchCategoriesOfReceipt(args.receiptId) {
            binding.itemsCount.text = it.size.toString()
            setupCategoryAdapter(it)
        }
    }

    private fun setupCategoryAdapter(categoryList: List<Category>) {
        with(binding.categoriesRecyclerView) {
            setHasFixedSize(true)
            adapter = ReceiptItemsAdapter(categoryList)/*.also {
                it.registerOnItemClickListener(this@ReceiptItemsDisplayFragment)
            }*/
        }
    }
}