package com.example.task.repository

import com.example.task.api.ApiClient
import com.example.task.model.CategoryListResponse
import retrofit2.Call
import retrofit2.Response

class CategoryRepository {

    fun getTopCategories(
        operationCallBack: OperationCallback<CategoryListResponse>,
        token: String,
        lang: String
    ) {
        ApiClient.apiService.getTopCategories("Bearer " + token, lang)
            .enqueue(object : retrofit2.Callback<CategoryListResponse> {
                override fun onResponse(
                    call: Call<CategoryListResponse>,
                    listResponse: Response<CategoryListResponse>
                ) {
                    operationCallBack.onSuccess((listResponse.body() as CategoryListResponse))
                }

                override fun onFailure(call: Call<CategoryListResponse>, t: Throwable) {
                    operationCallBack.onFailure()
                }
            })
    }
}