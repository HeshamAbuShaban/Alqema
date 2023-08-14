package com.alqema.controllers

import com.alqema.models.Account
import com.alqema.models.Category
import com.alqema.models.Unit
import com.alqema.models.constants.AccountType

class CreationController private constructor() {

    companion object {
        private var creationController: CreationController? = null

        @JvmStatic
        fun getInstance(): CreationController {
            if (creationController == null) {
                creationController = CreationController()
            }
            return creationController!!
        }
    }

    //.. create category

    fun createCategory(
        itemNumber: Int, categoryName: String, barcodeNumber: Int,
        mainUnit: Unit, sellingPrice: Double, purchasePrice: Double,
    ): Category {
        return Category.Builder()
            .withItemNumber(itemNumber)
            .withCategoryName(categoryName)
            .withBarcodeNumber(barcodeNumber)
            .withMainUnit(mainUnit)
            .withSellingPrice(sellingPrice)
            .withPurchasePrice(purchasePrice)
            .build()
    }

    //.. create account

    fun createAccount(
        accountNumber: Int, accountName: String, accountType: AccountType,
        address: String, mobileNumber: Int, belongsToAccount: Int,
    ): Account {
        return Account.Builder()
            .withAccountNumber(accountNumber)
            .withAccountName(accountName)
            .withAccountType(accountType)
            .withAddress(address)
            .withMobileNumber(mobileNumber)
            .withBelongsToAccount(belongsToAccount)
            .build()
    }
    
    //.. create unit

    fun createUnit(
         unitName: String,  quantityOfUnit: Int,  unitPrice: Double,
         unitBarcode: String,
    ): Unit {
        return Unit.Builder()
            .withUnitName(unitName)
            .withQuantityOfUnit(quantityOfUnit)
            .withUnitPrice(unitPrice)
            .withBarcode(unitBarcode)
            .build()
    }


}