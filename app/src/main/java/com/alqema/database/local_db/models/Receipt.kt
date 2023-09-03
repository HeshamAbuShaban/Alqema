package com.alqema.database.local_db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.alqema.database.type_converter.DateConverter

@Entity(
    tableName = "receipts",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["Account ID"],
            childColumns = ["Customer ID"],
            onDelete = ForeignKey.CASCADE, // Cascade delete when an associated account is deleted
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
@TypeConverters(DateConverter::class)
data class Receipt(
    @PrimaryKey(true)
    @ColumnInfo("Receipt ID")
    val receiptNumber: Int? = null,

    // this is the id of the Account PrimaryKey-ID class.
    @ColumnInfo("Customer ID")
    val accountNumber: Int,

    @ColumnInfo("Receipt Details")
    val receiptDetails: String?,
    @ColumnInfo("Receipt Date")
    val receiptDate: Long,
    // this is the id of the ReceiptCategory class.
    @ColumnInfo("Child Receipt ID")
    val categoryListIds: Int,

    // this is the id of the ReceiptCategory class.
    @ColumnInfo("Total")
    val total: Double,

    ) {
    class Builder {

        private var receiptNumber: Int = 1
        private var accountNumber: Int = 1
        private var receiptDetails: String = "a"
        private var receiptDate: Long = 1L
        private var barcodeNumber: String = "a"
        private var categoryListIds: Int = 1
        private var total: Double = 1.0

        fun withReceiptNumber(receiptNumber: Int): Builder {
            this.receiptNumber = receiptNumber
            return this
        }

        fun withAccountNumber(accountNumber: Int): Builder {
            this.accountNumber = accountNumber
            return this
        }

        fun withReceiptDetails(receiptDetails: String): Builder {
            this.receiptDetails = receiptDetails
            return this
        }

        fun withReceiptDate(receiptDate: Long): Builder {
            this.receiptDate = receiptDate
            return this
        }

        fun withBarcodeNumber(barcodeNumber: String): Builder {
            this.barcodeNumber = barcodeNumber
            return this
        }

        fun withCategoryListIds(categoryListIds: Int): Builder {
            this.categoryListIds = categoryListIds
            return this
        }

        fun withTotal(total: Double): Builder {
            this.total = total
            return this
        }


        // Build
        fun build(): Receipt =
            Receipt(
                accountNumber = accountNumber,
                receiptDetails = receiptDetails,
                receiptDate = receiptDate,
                categoryListIds = categoryListIds,
                total = total
            )
    }
}
