package com.example.task.repository

import com.example.task.api.ApiClient
import com.example.task.model.ProductListResponse
import com.example.task.model.ProductSingleResponse
import retrofit2.Call
import retrofit2.Response

class ProductRepository {

    fun getMostPopularProducts(
        operationCallBack: OperationCallback<ProductListResponse>,
        token: String,
        lang: String
    ) {
        ApiClient.apiService.getMostPopularProducts("Bearer " + token, lang)
            .enqueue(object : retrofit2.Callback<ProductListResponse> {
                override fun onResponse(
                    call: Call<ProductListResponse>,
                    listResponse: Response<ProductListResponse>
                ) {
                    operationCallBack.onSuccess((listResponse.body() as ProductListResponse))
                }

                override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                    operationCallBack.onFailure()
                }
            })
    }


    fun getProductById(
        operationCallBack: OperationCallback<ProductSingleResponse>,
        token: String,
        lang: String, productId: Int
    ) {
        ApiClient.apiService.getProductById("Bearer " + token, lang, productId)
            .enqueue(object : retrofit2.Callback<ProductSingleResponse> {
                override fun onResponse(
                    call: Call<ProductSingleResponse>,
                    listResponse: Response<ProductSingleResponse>
                ) {
                    operationCallBack.onSuccess((listResponse.body() as ProductSingleResponse))
                }

                override fun onFailure(call: Call<ProductSingleResponse>, t: Throwable) {
                    operationCallBack.onFailure()
                }
            })
    }
}