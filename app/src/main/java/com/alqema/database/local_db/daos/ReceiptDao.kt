package com.alqema.database.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.alqema.database.type_converter.DateConverter
import com.alqema.database.local_db.models.Receipt

@Dao
@TypeConverters(DateConverter::class)
interface ReceiptDao {

    @Query("SELECT * FROM receipts")
    fun observeReceipts(): LiveData<List<Receipt>>

    @Query("SELECT * FROM receipts WHERE `Receipt ID` LIKE '%' || :receiptId || '%'")
    fun observeReceipts(receiptId: Int): LiveData<List<Receipt>>

    @Query("SELECT * FROM receipts WHERE `Receipt ID` = :id")
    fun getReceipt(id: Int): LiveData<Receipt>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReceipt(receipt: Receipt)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateReceipt(receipt: Receipt)

    @Delete
    fun deleteReceipt(receipt: Receipt)

    @Query("DELETE FROM receipts WHERE `Receipt ID` = :id")
    fun deleteReceipt(id: Int)

}