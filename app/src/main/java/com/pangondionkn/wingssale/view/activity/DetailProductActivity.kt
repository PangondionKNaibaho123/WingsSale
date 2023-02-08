package com.pangondionkn.wingssale.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pangondionkn.wingssale.databinding.ActivityDetailProductBinding
import com.pangondionkn.wingssale.model.data_class.Login
import com.pangondionkn.wingssale.model.data_class.Product
import com.pangondionkn.wingssale.view.extension.Extension.NUMBERING_FORMAT.Companion.formatThousandSeparator

class DetailProductActivity : AppCompatActivity() {
    private val TAG = DetailProductActivity::class.java.simpleName
    private var _binding : ActivityDetailProductBinding?= null
    private val binding get() = _binding!!
    private lateinit var deliveredUser: String
    private lateinit var deliveredProduct: Product

    companion object{
        const val EXTRA_USER_LOGIN = "EXTRA_USER_LOGIN"
        const val EXTRA_DELIVERED_PRODUCT = "EXTRA_DELIVERED_PRODUCT"
        fun newIntent(context: Context, deliveredUser: String, deliveredProduct: Product): Intent = Intent(context, DetailProductActivity::class.java)
            .putExtra(EXTRA_USER_LOGIN, deliveredUser)
            .putExtra(EXTRA_DELIVERED_PRODUCT, deliveredProduct)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        deliveredUser = intent.getStringExtra(EXTRA_USER_LOGIN) as String
        deliveredProduct = intent.getParcelableExtra<Product>(EXTRA_DELIVERED_PRODUCT) as Product

        setUpView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpView(){
        binding.apply {
            tvNameProduct.text = deliveredProduct.product_name
            tvDimensionProduct.text = deliveredProduct.dimension

            when(deliveredProduct.discount == 0){
                true ->{
                    tvPriceProduct.text = "Rp ${deliveredProduct.price.formatThousandSeparator()},-"
                }
                false ->{
                    val discPrice = (100-(deliveredProduct.discount)) * deliveredProduct.price / 100
                    tvPriceProduct.text = "Rp ${discPrice.formatThousandSeparator()},-"
                }
            }
        }
    }
}