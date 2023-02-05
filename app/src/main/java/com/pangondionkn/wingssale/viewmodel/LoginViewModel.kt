package com.pangondionkn.wingssale.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pangondionkn.wingssale.model.data_class.Login
import com.pangondionkn.wingssale.model.data_local.LoginDao
import com.pangondionkn.wingssale.model.data_local.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private var loginDao: LoginDao?= null
    private var loginDB: LoginDatabase?= LoginDatabase.getDatabase(application)

    init {
        loginDao = loginDB?.loginDao()
    }

    fun addUserLogin(userLogin: Login){
        CoroutineScope(Dispatchers.IO).launch {
            val userLogin_ = Login(
                userLogin.user,
                userLogin.password
            )
            loginDao?.addUserLogin(userLogin_)
        }
    }

//    fun checkUserLogin(deliveredUser: String, deliveredPassword: String) = loginDao?.checkUserLogin(deliveredUser, deliveredPassword)

    fun getResultCheckUserLogin(deliveredUser: String, deliveredPassword: String): Int? = loginDao?.checkUserLogin(deliveredUser, deliveredPassword)

    fun removeAllUserLogin() = loginDao?.removeAllUser()
}