package com.pangondionkn.wingssale.view.adapter

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pangondionkn.wingssale.R
import com.pangondionkn.wingssale.databinding.RvItemproductBinding
import com.pangondionkn.wingssale.model.data_class.Product
import com.pangondionkn.wingssale.model.data_class.ProductnAmount
import com.pangondionkn.wingssale.view.extension.Extension.NUMBERING_FORMAT.Companion.formatThousandSeparator

class ListProductAdapter(
    var data: ArrayList<ProductnAmount>?= null,
    private val listener: ItemListener?= null
): RecyclerView.Adapter<ListProductAdapter.ItemHolder>() {

    private val TAG = ListProductAdapter::class.java.simpleName

    private var amountProduct = 0
    private var indexProduct = 0

    interface ItemListener{
        fun onItemClicked(item: Product, index: Int)
        fun addItemtoList(item:Product){}
        fun onItemPurchased(item: Product,index: Int, amount: Int)
//        fun onItemAmountAdded(item: Product)
    }

    fun getAmount(): Int{
        return amountProduct
    }

    fun getIndex():Int{
        return indexProduct
    }

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        fun addAmountfromDetail(item:Product) = with(itemView){
//            val binding = RvItemproductBinding.bind(itemView)
//            amountProduct++
//            binding.tvAmountProduct.text = amountProduct.toString()
//            binding.tvAmountProduct.visibility = View.VISIBLE
//        }

        fun bind(item: ProductnAmount, listener: ItemListener) = with(itemView){
            val binding = RvItemproductBinding.bind(itemView)
            binding.apply {
                tvNameproduct.text = item.product.product_name

                when(item.product.discount == 0){
                    true ->{
                        tvPricerealproduct.visibility = View.GONE
                        tvPricediscproduct.text = "Rp ${item.product.price.formatThousandSeparator()},-"
                    }
                    else ->{
                        tvPricerealproduct.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                        tvPricerealproduct.text = "Rp ${item.product.price},-"
                        val discPrice = (100-(item.product.discount)) * item.product.price / 100
                        Log.d(TAG, "disc price: $discPrice")
                        tvPricediscproduct.text = "Rp ${discPrice.formatThousandSeparator()},-"
                    }
                }

                btnBuyProduct.setOnClickListener {
                    item.amount++
                    tvAmountProduct.text = item.amount.toString()
                    tvAmountProduct.visibility = View.VISIBLE
                    indexProduct = adapterPosition
                    item.product.let { it -> listener.onItemPurchased(it, indexProduct, item.amount) }
//                    item.let { it -> listener.addItemtoList(it) }
                }

                root.setOnClickListener {
                    item.product.let { it -> listener.onItemClicked(it, indexProduct) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_itemproduct, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data?.get(position)!!, listener!!)
    }

    override fun getItemCount(): Int = data?.size!!
}