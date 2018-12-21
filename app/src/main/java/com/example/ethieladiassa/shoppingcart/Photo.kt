package com.example.ethieladiassa.shoppingcart

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("filename")
    var filename: String? = null
)