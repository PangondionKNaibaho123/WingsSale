package com.pangondionkn.wingssale.model.data_local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pangondionkn.wingssale.model.data_class.Login

@Database(
    entities = [Login::class],
    version = 1
)

abstract class LoginDatabase: RoomDatabase() {
    companion object{
        private var INSTANCE: LoginDatabase?= null

        fun getDatabase(context: Context): LoginDatabase?{
            if(INSTANCE == null){
                synchronized(LoginDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LoginDatabase::class.java,
                        "Login"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun loginDao(): LoginDao
}