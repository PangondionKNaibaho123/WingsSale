package com.pangondionkn.wingssale.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pangondionkn.wingssale.R
import com.pangondionkn.wingssale.databinding.ActivityCheckoutBinding
import com.pangondionkn.wingssale.model.data_class.TransactionDetail
import com.pangondionkn.wingssale.model.data_class.TransactionHeader
import com.pangondionkn.wingssale.view.adapter.ListCheckoutAdapter
import com.pangondionkn.wingssale.view.custom_ui.PopUpDialogListener
import com.pangondionkn.wingssale.view.custom_ui.showPopupDialog
import com.pangondionkn.wingssale.view.extension.Extension.NUMBERING_FORMAT.Companion.formatThousandSeparator
import com.pangondionkn.wingssale.view.extension.Extension.TIME.Companion.getCurrentDateTime
import com.pangondionkn.wingssale.viewmodel.CheckoutViewModel
import com.pangondionkn.wingssale.viewmodel.ListProductViewModel
import com.pangondionkn.wingssale.viewmodel.TransactionDetailViewModel
import java.text.SimpleDateFormat

class CheckoutActivity : AppCompatActivity() {
    private val TAG = CheckoutActivity::class.java.simpleName
    private var _binding : ActivityCheckoutBinding?= null
    private val binding get() = _binding!!
    private lateinit var deliveredUser: String
    private var arrListTransactionHeader = ArrayList<TransactionHeader>()
    private var listTransactionDetail = ArrayList<TransactionDetail>()
    private lateinit var dateToday: String
    private var totalSubTotal: Int = 0

    private lateinit var transactionDetailViewModel: TransactionDetailViewModel
    private lateinit var listProductViewModel: ListProductViewModel
    private lateinit var checkoutViewModel: CheckoutViewModel

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
        checkoutViewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]

        deliveredUser = intent.getStringExtra(EXTRA_USER) as String

        val formatDate = SimpleDateFormat("dd-MM-yyyy")
        dateToday = formatDate.format(getCurrentDateTime())

        setUpView()
    }

    private fun setUpView(){
        with(binding){
            rvListPurchaseProduct.setHasFixedSize(true)
            rvListPurchaseProduct.layoutManager = LinearLayoutManager(this@CheckoutActivity)
        }

        transactionDetailViewModel.getListTransactionDetail()!!.observe(this){listTransaction ->
            Log.d(TAG, "listTransaction : $listTransaction")

            listTransaction.forEach {
                listTransactionDetail.addAll(listOf(it))
            }

            val adapter = ListCheckoutAdapter(listTransaction, listProductViewModel)
            binding.rvListPurchaseProduct.adapter = adapter

            totalSubTotal = transactionDetailViewModel.getTotalPurchase(listTransactionDetail)
            binding.tvTotalPricePurchase.text = "Rp ${totalSubTotal.formatThousandSeparator()},-"

        }


        with(binding){
//            val adapter = ListCheckoutAdapter(listTransactionDetail, listProductViewModel)
//            rvListPurchaseProduct.adapter = adapter

//            val totalSubTotal = transactionDetailViewModel.getTotalPurchase(listTransactionDetail)
//            tvTotalPricePurchase.text = "Rp ${totalSubTotal.formatThousandSeparator()},-"

            btnConfirmPurchase.setOnClickListener {
                listTransactionDetail.forEach {
                    var transactionHeader = TransactionHeader(
                        document_code = it.document_code,
                        document_number = it.document_number,
                        user = deliveredUser,
                        total = totalSubTotal,
                        date = dateToday
                    )

                    arrListTransactionHeader.add(transactionHeader)
                }

                //Add to Transaction header database
                checkoutViewModel.addListTransactionHeader(arrListTransactionHeader)

                this@CheckoutActivity.showPopupDialog(getString(R.string.checkout_success), object :
                    PopUpDialogListener {
                    override fun onClickPopUpListener() {
                        closeOptionsMenu()
                    }
                })
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        transactionDetailViewModel.removeAllTransactionDetail()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        transactionDetailViewModel.removeAllTransactionDetail()
    }
}
