package com.alqema.models

import com.alqema.models.constants.AccountType

data class Unit(
    val unitName: String, val quantityOfUnit: Int, val unitPrice: Double,
    val unitBarcode: String,
) {
    class Builder {

        private var unitName: String = "default_"
        private var quantityOfUnit: Int = 1
        private var unitPrice: Double = 1.0
        private var unitBarcode: String = "111"


        fun withUnitName(unitName: String): Builder {
            this.unitName = unitName
            return this
        }

        fun withQuantityOfUnit(quantityOfUnit: Int): Builder {
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


        fun build(): Unit =
            Unit(unitName, quantityOfUnit, unitPrice, unitBarcode)

    }
}
