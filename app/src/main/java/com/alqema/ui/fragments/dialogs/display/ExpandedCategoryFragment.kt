package com.alqema.ui.fragments.dialogs.display

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alqema.database.local_db.models.Category
import com.alqema.databinding.FragmentExpandedCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExpandedCategoryFragment(val category: Category) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentExpandedCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentExpandedCategoryBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCategoryIntoScreen()
    }

    private fun setupCategoryIntoScreen() {
        with(binding) {
            with(category) {
                categoryId.text = category.categoryNumber.toString()
                binding.categoryName.text = categoryName
                categoryBarcode.text = category.barcodeNumber
                categoryMainUnit.text = category.mainUnit
                categorySellingPrice.text = category.sellingPrice.toString()
                categoryPurchasePrice.text = category.purchasePrice.toString()
                binding.unitName.text = category.unitName
                unitQuantity.text = category.quantityOfUnit.toString()
                binding.unitPrice.text = category.unitPrice.toString()
                binding.unitBarcode.text = category.unitBarcode
            }
        }
    }
}