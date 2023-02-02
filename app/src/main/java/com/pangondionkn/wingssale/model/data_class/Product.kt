package com.pangondionkn.wingssale.model.data_class

import android.os.Parcelable
import androidx.annotation.Size
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName


@Parcelize
@Entity(tableName = "Product")
data class Product(
    @field:SerializedName("Product Code")
    @field:Size(max = 18L)
    var product_code: String = "",

    @field:SerializedName("Product Name")
    @field:Size(max = 30L)
    var product_name: String = "",

    @field:SerializedName("Price")
    @field:Size(max = 6L)
    var price: Int = 0,

    @field:SerializedName("Currency")
    @field:Size(max = 5L)
    var currency: String = "",

    @field:SerializedName("Discount")
    @field:Size(max = 6L)
    var discount: Int = 0,

    @field:SerializedName("Dimension")
    @field:Size(max = 50L)
    var dimension: String = "",

    @field:SerializedName("Unit")
    @field:Size(max = 5L)
    var unit: String = ""
):Parcelable