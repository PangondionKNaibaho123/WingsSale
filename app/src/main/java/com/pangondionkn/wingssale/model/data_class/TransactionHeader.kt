package com.pangondionkn.wingssale.model.data_class

import android.os.Parcelable
import androidx.annotation.Size
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.sql.Date
import com.google.gson.annotations.SerializedName

@Parcelize
@Entity(tableName = "Transaction Header")
data class TransactionHeader(
    @field:SerializedName("Document Code")
    @field:Size(max = 3L)
    var document_code: String = "",

    @field:SerializedName("Document Number")
    @field:Size(max = 10L)
    var document_number: String = "",

    @field:SerializedName("User")
    @field:Size(max = 50L)
    @PrimaryKey(autoGenerate = false)
    var user: String = "",

    @field:SerializedName("Total")
    @field:Size(max = 10L)
    var total: Int = 0,

    @field:SerializedName("Date")
    @field:Size(max = 10L)
    var date: String,
): Parcelable