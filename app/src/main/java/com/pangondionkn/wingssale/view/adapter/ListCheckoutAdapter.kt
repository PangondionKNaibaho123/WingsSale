package com.pangondionkn.wingssale.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.pangondionkn.wingssale.R
import com.pangondionkn.wingssale.databinding.RvItemcheckoutBinding
import com.pangondionkn.wingssale.model.data_class.TransactionDetail
import com.pangondionkn.wingssale.view.extension.Extension.NUMBERING_FORMAT.Companion.formatThousandSeparator
import com.pangondionkn.wingssale.viewmodel.ListProductViewModel
import com.pangondionkn.wingssale.viewmodel.TransactionDetailViewModel

class ListCheckoutAdapter(
    var data: List<TransactionDetail>?= null,
    var listProductViewModel: ListProductViewModel
): RecyclerView.Adapter<ListCheckoutAdapter.ItemHolder>() {
    private val TAG = ListCheckoutAdapter::class.java.simpleName

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: TransactionDetail) = with(itemView){
            val binding = RvItemcheckoutBinding.bind(itemView)

            binding.apply {
                tvNamePurchased.text = listProductViewModel.getProductName(item.product_code)
                tvAmountPurchased.text = item.quantity.toString()
                tvSubTotalPrice.text = "Rp ${item.sub_total.formatThousandSeparator()},-"
                tvUnitPurchased.text = item.unit
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_itemcheckout, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data?.get(position)!!)
    }

    override fun getItemCount(): Int = data?.size!!
}