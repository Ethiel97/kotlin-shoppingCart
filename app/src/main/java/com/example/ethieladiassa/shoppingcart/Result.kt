package com.example.ethieladiassa.shoppingcart

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("products")
    var products: List<Product> = listOf()
)