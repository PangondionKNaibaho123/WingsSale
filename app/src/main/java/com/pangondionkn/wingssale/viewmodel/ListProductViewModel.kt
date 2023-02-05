package com.pangondionkn.wingssale.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pangondionkn.wingssale.model.data_class.Product
import com.pangondionkn.wingssale.model.data_local.ProductDao
import com.pangondionkn.wingssale.model.data_local.ProductDatabase

class ListProductViewModel(application: Application):AndroidViewModel(application) {
    private val TAG = ListProductViewModel::class.java.simpleName
    private var productDao: ProductDao?= null
    private var productDB: ProductDatabase?= ProductDatabase.getDatabase(application)

    init {
        productDao = productDB?.productDao()
    }

    fun addProduct(listProduct: List<Product>) {
        Log.d(TAG, "add product....")
        productDao?.addProduct(listProduct)
    }

    fun getListProducts(): LiveData<List<Product>>? = productDao?.getAllProduct()
}