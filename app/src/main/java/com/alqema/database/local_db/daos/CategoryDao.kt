package com.alqema.database.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alqema.models.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun observeCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM categories WHERE `Category Name` LIKE '%' || :name || '%'")
    fun observeCategories(name: String): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)

    @Query("DELETE FROM categories WHERE `Category ID` = :id")
    fun deleteCategory(id: Int)
}