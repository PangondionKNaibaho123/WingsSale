package com.pangondionkn.wingssale.model.data_dummy

import com.pangondionkn.wingssale.model.data_class.Login

object Dummy_Data {
    fun getDummyUser(): Login = Login(
        user = "pangondion",
        password = "naibahono123"
    )
}