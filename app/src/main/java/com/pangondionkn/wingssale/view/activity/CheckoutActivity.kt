package com.pangondionkn.wingssale.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pangondionkn.wingssale.databinding.ActivityCheckoutBinding
import com.pangondionkn.wingssale.model.data_class.TransactionDetail
import com.pangondionkn.wingssale.view.adapter.ListCheckoutAdapter
import com.pangondionkn.wingssale.viewmodel.ListProductViewModel
import com.pangondionkn.wingssale.viewmodel.TransactionDetailViewModel

class CheckoutActivity : AppCompatActivity() {
    private val TAG = CheckoutActivity::class.java.simpleName
    private var _binding : ActivityCheckoutBinding?= null
    private val binding get() = _binding!!
    private lateinit var deliveredUser: String
    private var arrListTransaction = ArrayList<TransactionDetail>()

    private lateinit var transactionDetailViewModel: TransactionDetailViewModel
    private lateinit var listProductViewModel: ListProductViewModel

    companion object{
        const val EXTRA_USER = "EXTRA_USER"
        fun newIntent(context: Context, deliveredUser:String): Intent = Intent(context, CheckoutActivity::class.java)
            .putExtra(EXTRA_USER, deliveredUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        transactionDetailViewModel = ViewModelProvider(this)[TransactionDetailViewModel::class.java]
        listProductViewModel = ViewModelProvider(this)[ListProductViewModel::class.java]

        deliveredUser = intent.getStringExtra(EXTRA_USER) as String

        setUpView()
    }

    private fun setUpView(){
        with(binding){
            rvListPurchaseProduct.setHasFixedSize(true)
            rvListPurchaseProduct.layoutManager = LinearLayoutManager(this@CheckoutActivity)
        }

        transactionDetailViewModel.getListTransactionDetail()!!.observe(this){listTransaction ->
            Log.d(TAG, "listTransaction : $listTransaction")
//            listTransaction.forEach {
//                arrListTransaction.addAll(listOf(it))
//            }
            val adapter = ListCheckoutAdapter(listTransaction, listProductViewModel)

            binding.rvListPurchaseProduct.adapter = adapter
        }

//        Log.d(TAG, "arrListTransaction : $arrListTransaction")
//
//        val adapter = ListCheckoutAdapter(arrListTransaction, listProductViewModel)

        with(binding){
            btnConfirmPurchase.setOnClickListener {
                Toast.makeText(this@CheckoutActivity, "Confirm Checkout", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
