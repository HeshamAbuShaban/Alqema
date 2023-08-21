package com.alqema.controllers

import com.alqema.database.local_db.models.Account
import com.alqema.database.local_db.models.Category
import com.alqema.database.local_db.models.constants.account.AccountCurrency
import com.alqema.database.local_db.models.constants.account.AccountDetails
import com.alqema.database.local_db.models.constants.account.AccountNature
import com.alqema.database.local_db.models.constants.account.AccountType

class CreationController private constructor() {

    //.. singleton
    companion object {
        @Volatile
        private var creationController: CreationController? = null

        @JvmStatic
        fun getInstance(): CreationController {
            return creationController ?: synchronized(this) {
                creationController ?: CreationController()
            }
        }
    }

    //.. create category
    fun createCategory(
        categoryNumber: Int, categoryName: String, barcodeNumber: Int,
        mainUnit: String, sellingPrice: Double, purchasePrice: Double,
        //.. appended this OldUnit .
        unitName: String, quantityOfUnit: Double, unitPrice: Double,
        unitBarcode: String,
    ): Category {
        return Category.Builder()
            .withCategoryNumber(categoryNumber)
            .withCategoryName(categoryName)
            .withBarcodeNumber(barcodeNumber)
            .withMainUnit(mainUnit)
            .withSellingPrice(sellingPrice)
            .withPurchasePrice(purchasePrice)
            // unit ues in the category
            .withUnitName(unitName)
            .withQuantityOfUnit(quantityOfUnit)
            .withUnitPrice(unitPrice)
            .withBarcode(unitBarcode)
            .build()
    }

    //.. create account
    fun createAccount(
        accountNumber: Int, accountName: String, accountDetails: AccountDetails,
        address: String, mobileNumber: String, belongsToAccount: Int,
        //.. New
        accountNature: AccountNature,
        accountType: AccountType,
        accountCurrency: AccountCurrency,
    ): Account {
        return Account.Builder()
            .withAccountNumber(accountNumber)
            .withAccountName(accountName)
            .withAccountDetails(accountDetails)
            .withAddress(address)
            .withMobileNumber(mobileNumber)
            .withBelongsToAccount(belongsToAccount)
            // added recently
            .withAccountNature(accountNature)
            .withAccountType(accountType)
            .withAccountCurrency(accountCurrency)
            .build()
    }

}