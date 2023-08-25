package com.alqema.database.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alqema.database.local_db.daos.AccountDao
import com.alqema.database.local_db.daos.CategoryDao
import com.alqema.database.local_db.daos.ReceiptCategoryDao
import com.alqema.database.local_db.daos.ReceiptDao
import com.alqema.database.local_db.models.Account
import com.alqema.database.local_db.models.Category
import com.alqema.database.local_db.models.Receipt
import com.alqema.database.local_db.models.ReceiptCategory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(
    entities = [Account::class, Category::class, Receipt::class, ReceiptCategory::class],
    version = 1,
    exportSchema = true
)
abstract class AlqemaDB : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun receiptDao(): ReceiptDao
    abstract fun receiptCategoryDao(): ReceiptCategoryDao


    companion object {
        private const val NUMBER_OF_THREADS: Int = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        @Volatile
        private var instance: AlqemaDB? = null

        @JvmStatic
        fun getDatabase(context: Context): AlqemaDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AlqemaDB {
            return Room.databaseBuilder(context, AlqemaDB::class.java, "alqema.db").build()
        }
    }

}