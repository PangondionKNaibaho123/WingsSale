package com.pangondionkn.wingssale.model.data_dummy

import com.pangondionkn.wingssale.model.data_class.Login
import com.pangondionkn.wingssale.model.data_class.Product

object Dummy_Data {

    fun getListDummyUser(): List<Login> = listOf(
        Login(user = "Smit", password = "_sm1t_OK"),
        Login(user = "testLogin1", password = "getLogin123"),
        Login(user = "testLogin2", password = "getLogin12345")
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
            product_code = "GVBRU",
            product_name = "Giv Biru",
            price = 11000,
            currency = "IDR",
            discount = 0,
            dimension = "6 cm x 4 cm",
            unit = "PCS"
        ),
        Product(
            product_code = "SKLNLQD",
            product_name = "So Klin Liquid",
            price = 18000,
            currency = "IDR",
            discount = 0,
            dimension = "13 cm x 10 cm",
            unit = "PCS"
        ),
        Product(
            product_code = "GVKNG",
            product_name = "Giv Kuning",
            price = 10000,
            currency = "IDR",
            discount = 0,
            dimension = "6 cm x 4 cm",
            unit = "PCS"
        )
    )
}