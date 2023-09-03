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
import com.alqema.database.local_db.models.Payment

@Dao
@TypeConverters(DateConverter::class)
interface PaymentDao {
    @Query("SELECT * FROM payments")
    fun observePayment(): LiveData<List<Payment>>

   /* @Query("SELECT * FROM payments WHERE `Payment ID` LIKE '%' || :paymentId || '%'")
    fun observePayment(paymentId: Int): LiveData<List<Payment>>

    @Query("SELECT * FROM payments WHERE `Payment ID` = :id")
    fun getPayment(id: Int): LiveData<Payment>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPayment(payment: Payment)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePayment(payment: Payment)

    @Delete
    fun deletePayment(payment: Payment)

    @Query("DELETE FROM payments WHERE `Id` = :id")
    fun deletePayment(id: Int)

}