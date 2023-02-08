package com.pangondionkn.wingssale.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.pangondionkn.wingssale.model.data_class.TransactionHeader
import com.pangondionkn.wingssale.model.data_local.TransactionHeaderDao
import com.pangondionkn.wingssale.model.data_local.TransactionHeaderDatabase

class CheckoutViewModel(application: Application): AndroidViewModel(application) {
    private val TAG = CheckoutViewModel::class.java.simpleName

    private var transactionHeaderDao: TransactionHeaderDao?= null
    private var transactionHeaderDB: TransactionHeaderDatabase?= TransactionHeaderDatabase.getDatabase(application)

    init {
        transactionHeaderDao = transactionHeaderDB?.transactionHeaderDao()
    }

    fun addListTransactionHeader(listTransactionHeader: ArrayList<TransactionHeader>){
        Log.d(TAG, "Adding to Transaction Header...")
        transactionHeaderDao?.addTransactionHeader(listTransactionHeader)
    }

}