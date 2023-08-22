package com.alqema.database.local_db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("receipt_categories")
data class ReceiptCategory(
    @PrimaryKey
    @ColumnInfo("R-C ID")
    val id: Int? = null,
    @ColumnInfo("Receipt Number")
    val receiptNumber: Int,
    @ColumnInfo("Category Number")
    val categoryNumber: Int,
)