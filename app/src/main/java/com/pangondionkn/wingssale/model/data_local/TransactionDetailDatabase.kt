package com.pangondionkn.wingssale.model.data_local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pangondionkn.wingssale.model.data_class.Product
import com.pangondionkn.wingssale.model.data_class.TransactionDetail

@Database(
    entities = [TransactionDetail::class],
    version = 1
)

abstract class TransactionDetailDatabase: RoomDatabase() {
    companion object{
        private var INSTANCE: TransactionDetailDatabase?= null

        fun getDatabase(context: Context):TransactionDetailDatabase?{
            if(INSTANCE == null){
                synchronized(TransactionDetailDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TransactionDetailDatabase::class.java,
                        "Transaction Detail"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun transactionDetailDao(): TransactionDetailDao
}