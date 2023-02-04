package com.pangondionkn.wingssale.model.data_local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pangondionkn.wingssale.model.data_class.Login

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserLogin(userLogin: Login)

    @Query("SELECT * FROM Login WHERE user LIKE :deliveredUser AND password LIKE :deliveredPassword")
    fun checkUserLogin(deliveredUser: String, deliveredPassword: String): Login

    @Query("DELETE FROM Login")
    fun removeAllUser()
}

interface ProductDao{

}

interface TransactionHeaderDao{

}

interface TransactionDetailDao{

}