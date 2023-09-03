package com.alqema.database.local_db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.alqema.database.type_converter.DateConverter

@Entity("payments")
@TypeConverters(DateConverter::class)
data class Payment(
    @PrimaryKey(true)
    @ColumnInfo("Id")
    val id: Int? = null,
    @ColumnInfo("Account ID")
    val accountID: Int,
    @ColumnInfo("Payment Details")
    val details: String?,
    @ColumnInfo("Payment amount")
    val payment: Double,
    @ColumnInfo("Payment Date")
    val date: Long,
) {
    class Builder {

        private var accountID: Int = 1
        private var payment: Double = 1.0
        private var details: String = ""
        private var date: Long = 1

        fun withAccountID(x: Int): Builder {
            this.accountID = x
            return this
        }

        fun withPayment(x: Double): Builder {
            this.payment = x
            return this
        }

        fun withDetails(x: String): Builder {
            this.details = x
            return this
        }

        fun withDate(x: Long): Builder {
            this.date = x
            return this
        }

        // Build
        fun build(): Payment =
            Payment(accountID = accountID, payment = payment, details = details, date = date)
    }
}
