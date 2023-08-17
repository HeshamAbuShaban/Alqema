package com.alqema.database.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.alqema.database.local_db.AlqemaDB
import com.alqema.database.local_db.daos.AccountDao
import com.alqema.database.local_db.daos.CategoryDao
import com.alqema.models.Account
import com.alqema.models.Category

class AlqemaRepository(application: Application) {

    private val accountDao: AccountDao
    private val categoryDao: CategoryDao
    private val db: AlqemaDB

    init {
        db = AlqemaDB.getDatabase(application)
        accountDao = db.accountDao()
        categoryDao = db.categoryDao()
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

    //.. Now Categories ----------------------------


    fun insertCategory(category: Category) :Boolean {
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

    fun observeCategories(): LiveData<List<Category>> = categoryDao.observeCategories()


    fun tearDown() {
        db.close()
    }

}