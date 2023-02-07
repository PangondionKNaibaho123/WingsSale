package com.pangondionkn.wingssale.model.data_class

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductnAmount(
    var product: Product,
    var amount: Int = 0
):Parcelable