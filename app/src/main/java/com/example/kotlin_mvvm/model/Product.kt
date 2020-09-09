package com.example.task.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("productName")
    @Expose
    var productName: String,
    @SerializedName("description")
    @Expose
    var description: String,
    @SerializedName("ingredients")
    @Expose
    var ingredients: String,
    @SerializedName("shelfLife")
    @Expose
    var shelfLife: String,
    @SerializedName("price")
    @Expose
    var price: Int,
    @SerializedName("measurementEnumValue")
    @Expose
    var measurementEnumValue: String,
    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String,
    @SerializedName("favorite")
    @Expose
    var favorite: Boolean
)