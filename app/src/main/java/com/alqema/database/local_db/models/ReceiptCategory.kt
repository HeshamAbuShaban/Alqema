package com.alqema.database.local_db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receipt_categories")
data class ReceiptCategory(
    @PrimaryKey(true)
    val id: Int? = null,
    @ColumnInfo("Receipt ID")
    val receiptNumber: Int,
    @ColumnInfo("Category ID")
    val categoryNumber: Int,
) {
    class Builder {
        //        private var id: Int? = 1
//        private var rCId: Int = 1
        private var receiptNumber: Int = 1
        private var categoryNumber: Int = 1

        /*fun withId(id: Int): Builder {
            this.id = id
            return this
        }*/

        /* fun withRCId(rCId: Int): Builder {
             this.rCId = rCId
             return this
         }*/

        fun withReceiptNumber(receiptNumber: Int): Builder {
            this.receiptNumber = receiptNumber
            return this
        }

        fun withCategoryNumber(categoryNumber: Int): Builder {
            this.categoryNumber = categoryNumber
            return this
        }

        fun build(): ReceiptCategory = ReceiptCategory(
            receiptNumber = receiptNumber,
            categoryNumber = categoryNumber
        )
    }
}