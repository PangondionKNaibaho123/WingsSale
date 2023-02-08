package com.pangondionkn.wingssale.model.data_local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pangondionkn.wingssale.model.data_class.TransactionHeader

@Database(
    entities = [TransactionHeader::class],
    version = 1
)

abstract class TransactionHeaderDatabase: RoomDatabase() {
    companion object{
        private var INSTANCE: TransactionHeaderDatabase?= null

        fun getDatabase(context:Context): TransactionHeaderDatabase?{
            if (INSTANCE == null){
                synchronized(TransactionHeaderDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TransactionHeaderDatabase::class.java,
                        "Transaction Header"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun transactionHeaderDao(): TransactionHeaderDao
}