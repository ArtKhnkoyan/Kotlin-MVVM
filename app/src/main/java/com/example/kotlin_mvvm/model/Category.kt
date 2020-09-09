package com.example.task.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(

    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("categoryName")
    @Expose
    var categoryName: String,
    @SerializedName("categoryColoursHexes")
    @Expose
    var categoryColoursHexes: CategoryColour,
    @SerializedName("categoryColoursEnumValue")
    @Expose
    var categoryColoursEnumValue: String,
    @SerializedName("productCount")
    @Expose
    var productCount: Int,
    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String
)