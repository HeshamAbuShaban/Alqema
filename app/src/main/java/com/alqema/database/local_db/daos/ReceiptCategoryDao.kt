package com.alqema.database.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alqema.database.local_db.models.ReceiptCategory
@Dao
interface ReceiptCategoryDao {

    @Query("SELECT * FROM receipt_categories")
    fun observeReceiptCategories(): LiveData<List<ReceiptCategory>>

    @Query("SELECT * FROM receipt_categories WHERE `R-C ID` LIKE '%' || :id || '%'")
    fun observeReceiptCategoriesById(id: Int): LiveData<List<ReceiptCategory>>

    @Query("SELECT * FROM receipt_categories WHERE `Receipt ID` LIKE '%' || :receiptId || '%'")
    fun observeReceiptCategoriesByReceiptId(receiptId: Int): LiveData<List<ReceiptCategory>>

    @Query("SELECT * FROM receipt_categories WHERE `R-C ID` = :id")
    fun getReceiptCategory(id: Int): LiveData<ReceiptCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReceiptCategory(receiptCategory: ReceiptCategory)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateReceiptCategory(receiptCategory: ReceiptCategory)

    @Delete
    fun deleteReceiptCategory(receiptCategory: ReceiptCategory)

    @Query("DELETE FROM receipt_categories WHERE `R-C ID` = :id")
    fun deleteReceiptCategory(id: Int)
}