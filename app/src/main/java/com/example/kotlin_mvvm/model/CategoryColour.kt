package com.example.task.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryColour(
    @SerializedName("dark")
    @Expose
    var dark: String,
    @SerializedName("medium")
    @Expose
    var medium: String,
    @SerializedName("light")
    @Expose
    var light: String
)