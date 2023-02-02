package com.pangondionkn.wingssale.model.data_class

import android.os.Parcelable
import androidx.annotation.Size
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Login")
data class Login(
    @field:SerializedName("User")
    @field:Size(max = 50L)
    var user: String = "",

    @field:SerializedName("Password")
    @field:Size(max = 255L)
    var password: String = ""
): Parcelable