package com.alqema.models

data class Category(
    val itemNumber: Int, val categoryName: String, val barcodeNumber: Int,
    val mainUnit: Unit, val sellingPrice: Double, val purchasePrice: Double,
) {
    class Builder {
        private var itemNumber: Int = 0
        private var categoryName: String = ""
        private var barcodeNumber: Int = 0
        private var mainUnit: Unit = Unit("default_", 1, 1.0, "111")
        private var sellingPrice: Double = 1.1
        private var purchasePrice: Double = 1.1

        fun withItemNumber(itemNumber: Int): Builder {
            this.itemNumber = itemNumber
            return this
        }

        fun withCategoryName(categoryName: String): Builder {
            this.categoryName = categoryName
            return this
        }

        fun withBarcodeNumber(barcodeNumber: Int): Builder {
            this.barcodeNumber = barcodeNumber
            return this
        }

        fun withMainUnit(mainUnit: Unit): Builder {
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

        fun build(): Category =
            Category(itemNumber, categoryName, barcodeNumber, mainUnit, sellingPrice, purchasePrice)

    }
}
