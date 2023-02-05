package com.pangondionkn.wingssale.model.data_local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pangondionkn.wingssale.model.data_class.Product

@Database(
    entities = [Product::class],
    version = 1
)

abstract class ProductDatabase: RoomDatabase() {
    companion object{
        private var INSTANCE: ProductDatabase?= null

        fun getDatabase(context: Context): ProductDatabase?{
            if(INSTANCE == null){
                synchronized(ProductDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDatabase::class.java,
                        "Product"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun productDao(): ProductDao
}