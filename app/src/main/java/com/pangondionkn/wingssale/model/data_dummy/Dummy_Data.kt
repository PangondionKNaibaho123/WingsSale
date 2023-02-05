package com.pangondionkn.wingssale.model.data_dummy

import com.pangondionkn.wingssale.model.data_class.Login
import com.pangondionkn.wingssale.model.data_class.Product

object Dummy_Data {
    fun getDummyUser(): Login = Login(
        user = "pangondion",
        password = "naibahono123"
    )

    fun getListDummyProduct(): List<Product> = listOf(
        Product(
            product_code = "SKUSKILNP",
            product_name = "So Klin Pewangi",
            price = 15000,
            currency = "IDR",
            discount = 10,
            dimension = "13 cm x 10 cm",
            unit = "PCS"
        ),
        Product(
            product_code = "KCPBNG",
            product_name = "Kecap Bango",
            price = 17000,
            currency = "IDR",
            discount = 12,
            dimension = "5 cm x 5 cm x 15cm",
            unit = "PCS"
        ),
        Product(
            product_code = "ASFFFFASS",
            product_name = "Nabati Coklat",
            price = 9000,
            currency = "IDR",
            discount = 0,
            dimension = "10 cm x 15 cm",
            unit = "PCS"
        ),
        Product(
            product_code = "NTRBST",
            product_name = "Nutri Boost",
            price = 7000,
            currency = "IDR",
            discount = 12,
            dimension = "5 cm x 15 cm",
            unit = "PCS"
        )
    )
}