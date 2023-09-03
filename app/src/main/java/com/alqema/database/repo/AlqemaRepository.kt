package com.alqema.database.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.alqema.database.listeners.Result
import com.alqema.database.local_db.AlqemaDB
import com.alqema.database.local_db.daos.AccountDao
import com.alqema.database.local_db.daos.CategoryDao
import com.alqema.database.local_db.daos.PaymentDao
import com.alqema.database.local_db.daos.ReceiptCategoryDao
import com.alqema.database.local_db.daos.ReceiptDao
import com.alqema.database.local_db.models.Account
import com.alqema.database.local_db.models.Category
import com.alqema.database.local_db.models.Payment
import com.alqema.database.local_db.models.Receipt
import com.alqema.database.local_db.models.ReceiptCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlqemaRepository(application: Application) {

    private val accountDao: AccountDao
    private val categoryDao: CategoryDao
    private val receiptDao: ReceiptDao
    private val receiptCategoryDao: ReceiptCategoryDao
    private val paymentDao: PaymentDao
    private val db: AlqemaDB

    init {
        db = AlqemaDB.getDatabase(application)
        accountDao = db.accountDao()
        categoryDao = db.categoryDao()
        receiptDao = db.receiptDao()
        receiptCategoryDao = db.receiptCategoryDao()
        paymentDao = db.paymentDao()
    }

    // Account Related Functions ==============================

    fun insertAccount(account: Account) {
        executeInBackground { accountDao.insertAccount(account) }
    }

    fun updateAccount(account: Account) {
        executeInBackground { accountDao.updateAccount(account) }
    }

    fun deleteAccount(account: Account) {
        executeInBackground { accountDao.deleteAccount(account) }
    }

    fun deleteAccount(id: Int) {
        executeInBackground { accountDao.deleteAccount(id) }
    }

    fun observeAccounts(): LiveData<List<Account>> = accountDao.observeAccounts()

    fun observeAccounts(name: String): LiveData<List<Account>> =
        accountDao.observeAccounts(name)

    fun getAccount(id: Int): LiveData<Account> = accountDao.getAccount(id)


    // append++++++++++++++++++++++++++
    suspend fun getAccounts(): List<Account> {
        return accountDao.getAccounts()
    }

    suspend fun getAccounts(name: String): List<Account> {
        return accountDao.getAccounts(name)
    }


    // Category Related Functions ==============================

    fun insertCategory(category: Category) {
        executeInBackground { categoryDao.insertCategory(category) }
    }

    fun updateCategory(category: Category) {
        executeInBackground { categoryDao.updateCategory(category) }
    }

    fun deleteCategory(category: Category) {
        executeInBackground { categoryDao.deleteCategory(category) }
    }

    fun deleteCategory(id: Int) {
        executeInBackground { categoryDao.deleteCategory(id) }
    }

    suspend fun getCategories(): List<Category> = categoryDao.getCategories()

    suspend fun getCategories(name: String): List<Category> = categoryDao.getCategories(name)

    fun observeCategories(): LiveData<List<Category>> = categoryDao.observeCategories()

    fun observeCategories(name: String): LiveData<List<Category>> =
        categoryDao.observeCategories(name)

    fun getCategory(id: Int): LiveData<Category> = categoryDao.getCategory(id)

    private fun observeCategory(id: Int): Category = categoryDao.observeCategory(id)

    // Receipt Related Functions ==============================

    fun insertReceipt(receipt: Receipt) {
        executeInBackground { receiptDao.insertReceipt(receipt) }
    }

    fun deleteReceipt(receipt: Receipt) {
        executeInBackground { receiptDao.deleteReceipt(receipt) }
    }

    fun deleteReceipt(receiptId: Int) {
        executeInBackground { receiptDao.deleteReceipt(receiptId) }
    }

    fun observeReceipts(): LiveData<List<Receipt>> = receiptDao.observeReceipts()

    fun observeReceipts(id: Int): LiveData<List<Receipt>> = receiptDao.observeReceipts(id)

    // Append ______________
    suspend fun getLastId(): Int {
        return receiptDao.getLastId()
    }

    // ReceiptCategory Related Functions ==============================

    fun insertReceiptCategory(receipt: ReceiptCategory) {
        executeInBackground { receiptCategoryDao.insertReceiptCategory(receipt) }
    }

    fun deleteReceiptCategory(receipt: ReceiptCategory) {
        executeInBackground { receiptCategoryDao.deleteReceiptCategory(receipt) }
    }

    /*fun deleteReceiptCategory(receiptId: Int) {
        executeInBackground { receiptCategoryDao.deleteReceiptCategory(receiptId) }
    }*/

    fun observeReceiptCategories(): LiveData<List<ReceiptCategory>> =
        receiptCategoryDao.observeReceiptCategories()

    fun observeReceiptCategoriesById(id: Int): LiveData<List<ReceiptCategory>> =
        receiptCategoryDao.observeReceiptCategoriesById(id)

    // Helper Function ==============================

    private fun executeInBackground(action: () -> Unit) {
        AlqemaDB.databaseWriteExecutor.execute { action.invoke() }
    }

    fun tearDown() {
        db.close()
    }

    // Relation Select
    private fun receiptObserveCategoriesById(id: Int): List<Int> {
        return receiptCategoryDao.observeCategoriesById(id)
    }

    suspend fun fetchCategoriesOfReceipt(id: Int): Result<List<Category>> {
        return try {
            val categories = withContext(Dispatchers.IO) {
                receiptObserveCategoriesById(id).map { observeCategory(it) }
            }
            Result.Success(categories)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    // Payment ==========================================================
    fun insertPayment(payment: Payment) {
        executeInBackground { paymentDao.insertPayment(payment) }
    }

    fun updatePayment(payment: Payment) {
        executeInBackground { paymentDao.updatePayment(payment) }
    }

    fun deletePayment(id: Int) {
        executeInBackground { paymentDao.deletePayment(id) }
    }

    fun observePayments(): LiveData<List<Payment>> = paymentDao.observePayment()

}
