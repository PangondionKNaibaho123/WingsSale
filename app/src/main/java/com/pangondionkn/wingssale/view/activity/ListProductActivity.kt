package com.pangondionkn.wingssale.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pangondionkn.wingssale.databinding.ActivityListProductBinding
import com.pangondionkn.wingssale.model.data_class.Login
import com.pangondionkn.wingssale.model.data_class.Product
import com.pangondionkn.wingssale.view.adapter.ListProductAdapter
import com.pangondionkn.wingssale.viewmodel.ListProductViewModel

class ListProductActivity : AppCompatActivity() {
    private val TAG = ListProductActivity::class.java.simpleName
    private var _binding: ActivityListProductBinding?= null
    private val binding get() = _binding!!
    private var arrListProduct = ArrayList<Product>()
    private lateinit var deliveredUser: String

    private lateinit var listProductViewModel: ListProductViewModel

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
        setUpRecylerView()
    }

    private fun setUpRecylerView(){
        with(binding){
            rvListProduct.setHasFixedSize(true)
            rvListProduct.layoutManager = LinearLayoutManager(this@ListProductActivity)
        }

        listProductViewModel.getListProducts()!!.observe(this){listProduct ->
            listProduct.forEach {
                arrListProduct.addAll(listOf(it))
            }

            val adapter = ListProductAdapter(arrListProduct, object: ListProductAdapter.ItemListener{
                override fun onItemClicked(item: Product) {
                    Log.d(TAG, "Selected Product: $item")
                    startActivity(
                        DetailProductActivity.newIntent(this@ListProductActivity, deliveredUser, item)
                    )
                }
            })

            binding.rvListProduct.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}