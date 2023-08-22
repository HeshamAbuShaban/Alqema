package com.alqema.ui.fragments.creation_ui.receipt

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.alqema.app_system.AppController
import com.alqema.database.local_db.models.Category
import com.alqema.database.repo.AlqemaRepository
import com.alqema.databinding.FragmentAddCategoryBinding

class ReceiptViewModel : ViewModel() {

    private var receiptNumber: Int? = null
    private var accountNumber: Int? = null
    private var receiptDetails: String? = null
    private var receiptDate: Long? = null
    private var barcodeNumber: String? = null

    private var categoryListIds: Int? = null


    private var categoryNumber: Int? = null
    private var categoryName: String? = null
    private var categoryBarcodeNumber: Int? = null
    private var categoryMainUnit: String? = null
    private var categorySellingPrice: Double? = null
    private var categoryPurchasePrice: Double? = null
    private var unitName: String? = null
    private var quantityOfUnit: Double? = null
    private var unitPrice: Double? = null
    private var unitBarcode: String? = null

    private lateinit var _binding: FragmentAddCategoryBinding
    private val binding get() = _binding
    private val repo: AlqemaRepository = AlqemaRepository(AppController.getInstance())


    fun performCreation(
        binding: FragmentAddCategoryBinding,
        function: () -> Unit,
        doneCreation: () -> Unit,
    ) {
        this._binding = binding
        readInputsText()
        if (checkData()) create(doneCreation) else function()
    }

    private fun readInputsText() {
        with(binding) {
            categoryNumber = edCategoryId.text.toInt()
            categoryName = edCategoryName.text.toString()
            categoryBarcodeNumber = edCategoryBarcode.text.toInt()
            categoryMainUnit = edCategoryMainUnit.text.toString()
            unitBarcode = edCategoryUnitBarcode.text.toString()
        }
    }

    private fun checkData(): Boolean =
        categoryNumber != null
                && categoryName != null
                && categoryBarcodeNumber != null
                && categoryMainUnit != null
                && categorySellingPrice != null
                && categoryPurchasePrice != null
                && unitName != null
                && quantityOfUnit != null
                && unitPrice != null
                && unitBarcode != null


    private fun create(doneCreation: () -> Unit) {
        // Create the instance
        val categoryInstance = Category.Builder()
            .withCategoryNumber(categoryNumber!!)
            .withCategoryName(categoryName!!)
            .withBarcodeNumber(categoryBarcodeNumber!!)
            .withMainUnit(categoryMainUnit!!)
            .withSellingPrice(categorySellingPrice!!)
            .withPurchasePrice(categoryPurchasePrice!!)
            // unit ues in the category
            .withUnitName(unitName!!)
            .withQuantityOfUnit(quantityOfUnit!!)
            .withUnitPrice(unitPrice!!)
            .withBarcode(unitBarcode!!)
            .build()

        repo.insertCategory(categoryInstance)
        doneCreation()
        clearInputs()
    }

    private fun clearInputs() {
        with(binding) {
            edCategoryId.setText("")
            edCategoryName.setText("")
            edCategoryBarcode.setText("")
            edCategoryMainUnit.setText("")
            edCategorySellingPrice.setText("")
            edCategoryPurchasePrice.setText("")
            edCategoryUnitName.setText("")
            edCategoryQuantityOfUnit.setText("")
            edCategoryUnitPrice.setText("")
            edCategoryUnitBarcode.setText("")
        }
    }

    /*    override fun onCleared() {
            super.onCleared()
            repo.tearDown()
        }*/

    fun setUIData(
        binding: FragmentAddCategoryBinding,
        category: Category,
        handleDeletion: () -> Unit,
    ) {
        with(binding) {
            handleDeletion()
            edCategoryId.isEnabled = false
            edCategoryId.setText(category.categoryNumber.toString())
            edCategoryName.setText(category.categoryName)
            edCategoryBarcode.setText(category.barcodeNumber.toString())
            edCategoryMainUnit.setText(category.mainUnit)
            edCategorySellingPrice.setText(category.sellingPrice.toString())
            edCategoryPurchasePrice.setText(category.purchasePrice.toString())
            edCategoryUnitName.setText(category.unitName)
            edCategoryQuantityOfUnit.setText(category.quantityOfUnit.toString())
            edCategoryUnitPrice.setText(category.unitPrice.toString())
            edCategoryUnitBarcode.setText(category.unitBarcode)
        }
    }

    fun preformDelete(id: Int,onDone: () -> Unit) {
        repo.deleteCategory(id = id)
        onDone()
    }
}

private fun Editable?.toInt(): Int? {
    if (this?.isNotEmpty() == true) return this.toString().toInt()
    return null
}
