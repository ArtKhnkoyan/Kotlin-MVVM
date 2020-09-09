package com.example.task.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductListResponse(

    @SerializedName("success")
    @Expose
    var success: Boolean,
    @SerializedName("data")
    @Expose
    var products: List<Product>,
    @SerializedName("message")
    @Expose
    var message: String
)