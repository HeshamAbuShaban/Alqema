package com.alqema.database.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.alqema.database.local_db.AlqemaDB
import com.alqema.database.local_db.daos.AccountDao
import com.alqema.database.local_db.daos.CategoryDao
import com.alqema.database.local_db.daos.ReceiptCategoryDao
import com.alqema.database.local_db.daos.ReceiptDao
import com.alqema.database.local_db.models.Account
import com.alqema.database.local_db.models.Category
import com.alqema.database.local_db.models.Receipt
import com.alqema.database.local_db.models.ReceiptCategory

class AlqemaRepository(application: Application) {

    private val accountDao: AccountDao
    private val categoryDao: CategoryDao
    private val receiptDao: ReceiptDao
    private val receiptCategoryDao: ReceiptCategoryDao
    private val db: AlqemaDB

    init {
        db = AlqemaDB.getDatabase(application)
        accountDao = db.accountDao()
        categoryDao = db.categoryDao()
        receiptDao = db.receiptDao()
        receiptCategoryDao = db.receiptCategoryDao()
    }

    fun insertAccount(account: Account) {
        AlqemaDB.databaseWriteExecutor.execute { accountDao.insertAccount(account) }
    }

    fun updateAccount(account: Account) {
        AlqemaDB.databaseWriteExecutor.execute { accountDao.updateAccount(account) }
    }

    fun deleteAccount(account: Account) {
        AlqemaDB.databaseWriteExecutor.execute { accountDao.deleteAccount(account) }
    }

    fun deleteAccount(id: Int) {
        AlqemaDB.databaseWriteExecutor.execute { accountDao.deleteAccount(id) }
    }

    fun observeAccounts(): LiveData<List<Account>> = accountDao.observeAccounts()

    fun observeAccounts(name: String): LiveData<List<Account>> {
        return accountDao.observeAccounts(name)
    }

    fun getAccount(id: Int): LiveData<Account> {
        return accountDao.getAccount(id)
    }

    //.. Now Categories ----------------------------


    fun insertCategory(category: Category): Boolean {
        AlqemaDB.databaseWriteExecutor.execute { categoryDao.insertCategory(category) }
        return true
    }

    fun updateCategory(category: Category) {
        AlqemaDB.databaseWriteExecutor.execute { categoryDao.updateCategory(category) }
    }

    fun deleteCategory(category: Category) {
        AlqemaDB.databaseWriteExecutor.execute { categoryDao.deleteCategory(category) }
    }

    fun deleteCategory(id: Int) {
        AlqemaDB.databaseWriteExecutor.execute { categoryDao.deleteCategory(id) }
    }

    // Pass
    suspend fun getCategories(): List<Category> {
        return categoryDao.getCategories()
    }

    // Test
    suspend fun getCategories(name: String): List<Category> {
        return categoryDao.getCategories(name)
    }


    fun observeCategories(): LiveData<List<Category>> = categoryDao.observeCategories()
    fun observeCategoriesArrayList(): LiveData<List<Category>> =
        categoryDao.observeCategoriesArrayList()

    fun observeCategories(name: String): LiveData<List<Category>> {
        return categoryDao.observeCategories(name)
    }

    fun getCategory(id: Int): LiveData<Category> {
        return categoryDao.getCategory(id)
    }

    fun tearDown() {
        db.close()
    }

    // Pass
    suspend fun getAccounts(): List<Account> {
        return accountDao.getAccounts()
    }

    // Test
    suspend fun getAccounts(name: String): List<Account> {
        return accountDao.getAccounts(name)
    }

    // ReceiptDao =======================================================

    fun insertReceipt(receipt: Receipt) {
        AlqemaDB.databaseWriteExecutor.execute { receiptDao.insertReceipt(receipt) }
    }
    // Deletion
    fun deleteReceipt(receipt: Receipt) {
        AlqemaDB.databaseWriteExecutor.execute { receiptDao.deleteReceipt(receipt) }
    }
    fun deleteReceipt(receiptId: Int) {
        AlqemaDB.databaseWriteExecutor.execute { receiptDao.deleteReceipt(receiptId) }
    }
    // observe
    fun observeReceipts():LiveData<List<Receipt>>{
        return receiptDao.observeReceipts()
    }
    fun observeReceipts(id: Int):LiveData<List<Receipt>>{
        return receiptDao.observeReceipts(id)
    }

    // ReceiptCategoryDao =======================================================

    fun insertReceiptCategory(receipt: ReceiptCategory) {
        AlqemaDB.databaseWriteExecutor.execute { receiptCategoryDao.insertReceiptCategory(receipt) }
    }
    // Deletion
    fun deleteReceiptCategory(receipt: ReceiptCategory) {
        AlqemaDB.databaseWriteExecutor.execute { receiptCategoryDao.deleteReceiptCategory(receipt) }
    }
    fun deleteReceiptCategory(receiptId: Int) {
        AlqemaDB.databaseWriteExecutor.execute { receiptCategoryDao.deleteReceiptCategory(receiptId) }
    }
    // observe
    fun observeReceiptCategories():LiveData<List<ReceiptCategory>>{
        return receiptCategoryDao.observeReceiptCategories()
    }
    fun observeReceiptCategoriesById(id: Int):LiveData<List<ReceiptCategory>>{
        return receiptCategoryDao.observeReceiptCategoriesById(id)
    }
    fun observeReceiptCategoriesByReceiptId(id: Int):LiveData<List<ReceiptCategory>>{
        return receiptCategoryDao.observeReceiptCategoriesByReceiptId(id)
    }
}