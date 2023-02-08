package com.pangondionkn.wingssale.viewmodel

import android.app.Application
import android.util.Log
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

    fun addListTransactionDetail(listTransDetail: ArrayList<TransactionDetail>){
        Log.d(TAG, "Adding to Purchased...")
        transactionDetailDao?.addTransactionDetail(listTransDetail)
    }

    fun getListTransactionDetail(): LiveData<List<TransactionDetail>>? = transactionDetailDao?.getListTransactionDetail()

    fun getTotalPurchase(listTransactionDetail: List<TransactionDetail>): Int{
        var result = 0
        for(i in 0..listTransactionDetail.size-1){
            result+=listTransactionDetail.get(i).sub_total
        }

        return result
    }

    fun removeAllTransactionDetail() = transactionDetailDao?.removeAllTransactionDetail()
}