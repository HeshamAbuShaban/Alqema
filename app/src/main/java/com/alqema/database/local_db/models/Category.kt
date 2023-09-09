package com.alqema.database.local_db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alqema.adapters.listeners.CategoryContract

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey
    @ColumnInfo("Category ID")
    override val categoryNumber: Int,
    @ColumnInfo("Category Name")
    override val categoryName: String,
    @ColumnInfo("Category Barcode")
    val barcodeNumber: String,
    @ColumnInfo("Category MainUnit")
    val mainUnit: String,
    @ColumnInfo("Category SellingPrice")
    val sellingPrice: Double,
    @ColumnInfo("Category PurchasePrice")
    val purchasePrice: Double,

    //.. appended this OldUnit .

    @ColumnInfo("Unit Name")
    val unitName: String,
    @ColumnInfo("Quantity Of Unit")
    val quantityOfUnit: Double,
    @ColumnInfo("Unit Price")
    val unitPrice: Double,
    @ColumnInfo("Unit Barcode")
    val unitBarcode: String,
) : CategoryContract {
    class Builder {

        private var categoryNumber: Int = 0
        private var categoryName: String = ""
        private var barcodeNumber: String = "0"
        private var mainUnit: String = "a1"
        private var sellingPrice: Double = 1.1
        private var purchasePrice: Double = 1.1

        fun withCategoryNumber(categoryNumber: Int): Builder {
            this.categoryNumber = categoryNumber
            return this
        }

        fun withCategoryName(categoryName: String): Builder {
            this.categoryName = categoryName
            return this
        }

        fun withBarcodeNumber(barcodeNumber: String): Builder {
            this.barcodeNumber = barcodeNumber
            return this
        }

        fun withMainUnit(mainUnit: String): Builder {
            this.mainUnit = mainUnit
            return this
        }

        fun withSellingPrice(sellingPrice: Double): Builder {
            this.sellingPrice = sellingPrice
            return this
        }

        fun withPurchasePrice(purchasePrice: Double): Builder {
            this.purchasePrice = purchasePrice
            return this
        }

        //--------------------UNIT

        private var unitName: String = "default_"
        private var quantityOfUnit: Double = 1.0
        private var unitPrice: Double = 1.0
        private var unitBarcode: String = "191"


        fun withUnitName(unitName: String): Builder {
            this.unitName = unitName
            return this
        }

        fun withQuantityOfUnit(quantityOfUnit: Double): Builder {
            this.quantityOfUnit = quantityOfUnit
            return this
        }

        fun withUnitPrice(unitPrice: Double): Builder {
            this.unitPrice = unitPrice
            return this
        }

        fun withBarcode(unitBarcode: String): Builder {
            this.unitBarcode = unitBarcode
            return this
        }

        // Build

        fun build(): Category =
            Category(
                categoryNumber,
                categoryName,
                barcodeNumber,
                mainUnit,
                sellingPrice,
                purchasePrice,
                /* appended this OldUnit*/
                unitName,
                quantityOfUnit,
                unitPrice,
                unitBarcode
            )

    }
}
