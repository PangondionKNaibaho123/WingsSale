package com.pangondionkn.wingssale.model.data_local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pangondionkn.wingssale.model.data_class.Login
import com.pangondionkn.wingssale.model.data_class.Product
import com.pangondionkn.wingssale.model.data_class.TransactionDetail

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserLogin(userLogin: Login)

    @Query("SELECT COUNT(*) FROM Login WHERE user LIKE :deliveredUser AND password LIKE :deliveredPassword")
    fun checkUserLogin(deliveredUser: String, deliveredPassword: String): Int

    @Query("DELETE FROM Login")
    fun removeAllUser()
}

@Dao
interface ProductDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(listProduct: List<Product>)

    @Query("SELECT * FROM Product")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("SELECT product_name FROM Product WHERE product_code = :productCode")
    fun getProductName(productCode: String): String

    @Query("DELETE FROM Product")
    fun removeAllProduct()
}

interface TransactionHeaderDao{

}

@Dao
interface TransactionDetailDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTransactionDetail(listTransactionDetail: ArrayList<TransactionDetail>)

    @Query("SELECT * FROM `Transaction Detail`")
    fun getListTransactionDetail(): LiveData<List<TransactionDetail>>
}