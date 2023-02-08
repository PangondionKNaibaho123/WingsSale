package com.pangondionkn.wingssale.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pangondionkn.wingssale.R
import com.pangondionkn.wingssale.databinding.ActivityListProductBinding
import com.pangondionkn.wingssale.model.data_class.Product
import com.pangondionkn.wingssale.model.data_class.ProductnAmount
import com.pangondionkn.wingssale.model.data_class.TransactionDetail
import com.pangondionkn.wingssale.view.adapter.ListProductAdapter
import com.pangondionkn.wingssale.view.custom_ui.PopUpDialogListener
import com.pangondionkn.wingssale.view.custom_ui.showPopupDialog
import com.pangondionkn.wingssale.view.extension.Extension.TIME.Companion.getCurrentDateTime
import com.pangondionkn.wingssale.viewmodel.ListProductViewModel
import com.pangondionkn.wingssale.viewmodel.TransactionDetailViewModel
import java.text.SimpleDateFormat

class ListProductActivity : AppCompatActivity() {
    private val TAG = ListProductActivity::class.java.simpleName
    private var _binding: ActivityListProductBinding?= null
    private val binding get() = _binding!!
    private var arrListProduct = ArrayList<Product>()
    private var arrListProductnAmount = ArrayList<ProductnAmount>()
    private lateinit var deliveredUser: String
    private var listPurchasedProducts: ArrayList<TransactionDetail> = ArrayList()

    private lateinit var listProductViewModel: ListProductViewModel
    private lateinit var transactionDetailViewModel: TransactionDetailViewModel

    companion object{
        const val EXTRA_USER = "EXTRA_USER"
        fun newIntent(context: Context, deliveredUser: String): Intent = Intent(context, ListProductActivity::class.java)
            .putExtra(EXTRA_USER, deliveredUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        deliveredUser = intent.getStringExtra(EXTRA_USER) as String
        listProductViewModel = ViewModelProvider(this)[ListProductViewModel::class.java]
        transactionDetailViewModel = ViewModelProvider(this)[TransactionDetailViewModel::class.java]
        setUpView()
    }

    private fun setUpView(){
        with(binding){
            rvListProduct.setHasFixedSize(true)
            rvListProduct.layoutManager = LinearLayoutManager(this@ListProductActivity)
        }

        listProductViewModel.getListProducts()!!.observe(this){listProduct ->
            listProduct.forEach {
                arrListProduct.addAll(listOf(it))
            }

            arrListProduct.forEach {
                val productnAmount = ProductnAmount(it, 0)
                arrListProductnAmount.add(productnAmount)
            }

            val adapter = ListProductAdapter(arrListProductnAmount, object: ListProductAdapter.ItemListener{
                override fun onItemClicked(item: Product, index: Int) {
                    Log.d(TAG, "Selected Product: $item")
                    startActivity(
                        DetailProductActivity.newIntent(this@ListProductActivity, deliveredUser, item)
                    )
                }

                override fun onItemPurchased(item: Product, index: Int, amount: Int) {
                    Log.d(TAG,"Selected index : $index")
                    val realPrice = when(item.discount == 0){
                        true ->{
                            item.price
                        }
                        else ->{
                            (100-(item.discount)) * item.price / 100
                        }
                    }
                    val formatDate = SimpleDateFormat("ddMMyyyy")
                    val number = formatDate.format(getCurrentDateTime())


                    val transactionDetail_e1 =
                        TransactionDetail(
                            document_code = "TRX",
                            document_number = number,
                            product_code = item.product_code,
                            price = realPrice,
                            quantity = amount,
                            unit = "PCS",
                            sub_total = (realPrice * amount),
                            currency = "IDR"
                        )
                    Log.d(TAG, "transactionDetail : $transactionDetail_e1")
                    val indexChecked = checkIndexWhereDuplicate(transactionDetail_e1, listPurchasedProducts)
                    Log.d(TAG, "indexChecked = $indexChecked")

                    if(indexChecked != -1){
                        listPurchasedProducts.set(indexChecked, transactionDetail_e1)
                    }else{
                        listPurchasedProducts.add(transactionDetail_e1)
                    }

                    Log.d(TAG, "list Purchased Products : $listPurchasedProducts")

                }
            })

            with(binding){
                rvListProduct.adapter = adapter

                binding.btnCheckoutProduct.setOnClickListener {
                    when(listPurchasedProducts.size == 0){
                        true ->{
                            this@ListProductActivity.showPopupDialog(getString(R.string.warning_checkout_product), object : PopUpDialogListener{
                                override fun onClickPopUpListener() {
                                    closeOptionsMenu()
                                }
                            })
                        }
                        else ->{
                            transactionDetailViewModel.addListTransactionDetail(listPurchasedProducts)
                            startActivity(
                                CheckoutActivity.newIntent(this@ListProductActivity, deliveredUser)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun checkIndexWhereDuplicate(transactionDetail: TransactionDetail, listTransactionDetail: List<TransactionDetail>): Int{
        //returns the index value when the item you want to add is similar to the item in the list
        val result = listTransactionDetail.indexOfFirst { it.product_code == transactionDetail.product_code }
        return result
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        listProductViewModel.removeAllProduct()
    }
}