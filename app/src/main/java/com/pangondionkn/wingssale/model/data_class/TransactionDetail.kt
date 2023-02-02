package com.pangondionkn.wingssale.model.data_class

import android.os.Parcelable
import androidx.annotation.Size
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName


@Parcelize
@Entity(tableName = "Transaction Detail")
data class TransactionDetail(
    @field:SerializedName("Document Code")
    @field:Size(max = 3L)
    var document_code: String = "",

    @field:SerializedName("Document Number")
    @field:Size(max = 10L)
    var document_number: String = "",

    @field:SerializedName("Product Code")
    @field:Size(max = 18L)
    var product_code: String = "",

    @field:SerializedName("Price")
    @field:Size(max = 6L)
    var price: Int = 0,

    @field:SerializedName("Quantity")
    @field:Size(max = 6L)
    var quantity: Int = 0,

    @field:SerializedName("Unit")
    @field:Size(max = 5L)
    var unit: String = "",

    @field:SerializedName("Sub Total")
    @field:Size(max = 10L)
    var sub_total: Int = 0,

    @field:SerializedName("Currency")
    @field:Size(max = 5L)
    var currency: String = ""
):Parcelable