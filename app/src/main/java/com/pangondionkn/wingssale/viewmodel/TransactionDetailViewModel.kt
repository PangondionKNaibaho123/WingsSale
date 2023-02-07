package com.pangondionkn.wingssale.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pangondionkn.wingssale.model.data_class.TransactionDetail
import com.pangondionkn.wingssale.model.data_local.TransactionDetailDao
import com.pangondionkn.wingssale.model.data_local.TransactionDetailDatabase

class TransactionDetailViewModel(application: Application): AndroidViewModel(application) {
    private val TAG = TransactionDetailViewModel::class.java.simpleName

    private var transactionDetailDao: TransactionDetailDao?= null
    private var transactionDetailDB: TransactionDetailDatabase?= TransactionDetailDatabase.getDatabase(application)

    init {
        transactionDetailDao = transactionDetailDB?.transactionDetailDao()
    }

    fun addListTransactionDetail(listTransDetail: List<TransactionDetail>){
        transactionDetailDao?.addTransactionDetail(listTransDetail)
    }

    fun getListTransactionDetail(): LiveData<List<TransactionDetail>>? = transactionDetailDao?.getListTransactionDetail()
}