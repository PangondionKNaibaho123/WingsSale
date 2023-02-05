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
import com.pangondionkn.wingssale.view.activity.ListProductActivity

class ListProductAdapter(
    var data: ArrayList<Product>?= null,
    private val listener: ItemListener?= null
): RecyclerView.Adapter<ListProductAdapter.ItemHolder>() {

    private val TAG = ListProductAdapter::class.java.simpleName

    interface ItemListener{
        fun onItemClicked(item: Product)
    }

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Product, listener: ItemListener) = with(itemView){
            val binding = RvItemproductBinding.bind(itemView)
            binding.apply {
                tvNameproduct.text = item.product_name
//                tvPricerealproduct.text = item.price.toString()
//
//                val discPrice = (100-item.discount)/100 * item.price
//
//                tvPricediscproduct.text = discPrice.toString()

                when(item.discount == 0){
                    true ->{
                        tvPricerealproduct.visibility = View.GONE
                        tvPricediscproduct.text = "Rp ${item.price},-"
                    }
                    else ->{
                        tvPricerealproduct.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                        tvPricerealproduct.text = "Rp ${item.price},-"
                        val discPrice = (100-(item.discount)) * item.price / 100
                        Log.d(TAG, "disc price: $discPrice")
                        tvPricediscproduct.text = "Rp $discPrice,-"
                    }
                }

                root.setOnClickListener {
                    item.let { it -> listener.onItemClicked(it) }
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