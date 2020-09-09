package com.example.task.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryListResponse(
    @SerializedName("success")
    @Expose
    var success: Boolean,
    @SerializedName("data")
    @Expose
    var categories: List<Category>,
    @SerializedName("message")
    @Expose
    var message: String
)