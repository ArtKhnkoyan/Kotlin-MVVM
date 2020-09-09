package com.example.task.api

import com.example.task.model.CategoryListResponse
import com.example.task.model.ProductListResponse
import com.example.task.model.ProductSingleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("/api/category/getTopCategoriesForMobile")
    fun getTopCategories(
        @Header("Authorization") token: String,
        @Header("languageName") lang: String
    ): Call<CategoryListResponse>

    @GET("/api/product/getMostPopularProducts")
    fun getMostPopularProducts(
        @Header("Authorization") token: String,
        @Header("languageName") lang: String
    ): Call<ProductListResponse>

    @GET("/api/product/getProductById")
    fun getProductById(
        @Header("Authorization") token: String,
        @Header("languageName") lang: String,
        @Query("id") productId: Int
    ): Call<ProductSingleResponse>
}