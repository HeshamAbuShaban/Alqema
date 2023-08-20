package com.alqema.database.local_db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alqema.models.Account

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts")
    fun observeAccounts(): LiveData<List<Account>>

    @Query("SELECT * FROM accounts WHERE `Account Name` LIKE '%' || :name || '%'")
    fun observeAccounts(name: String): LiveData<List<Account>>

    @Query("SELECT * FROM accounts WHERE `Account ID` = :id")
    fun getAccount(id: Int): LiveData<Account>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: Account)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAccount(account: Account)

    @Delete
    fun deleteAccount(account: Account)

    @Query("DELETE FROM accounts WHERE `Account ID` = :id")
    fun deleteAccount(id: Int)

}