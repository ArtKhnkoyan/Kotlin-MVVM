package com.example.task.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.model.Product
import com.example.task.model.ProductListResponse
import com.example.task.model.ProductSingleResponse
import com.example.task.repository.OperationCallback
import com.example.task.repository.ProductRepository

class ProductViewModel : ViewModel() {

    private val productRepository: ProductRepository = ProductRepository()
    var productList = MutableLiveData<List<Product>>()
    var product = MutableLiveData<Product>()

    init {
        productList.value = listOf()
    }

    fun getMostPopularProducts(
        token: String,
        lang: String
    ) {
        productRepository.getMostPopularProducts(object : OperationCallback<ProductListResponse> {
            override fun onSuccess(data: ProductListResponse) {
                productList.value = data.products
            }

            override fun onFailure() {
                productList.postValue(null)
            }
        }, token, lang)
    }

    fun getProductById(token: String, lang: String, productId: Int) {
        productRepository.getProductById(object : OperationCallback<ProductSingleResponse> {
            override fun onSuccess(data: ProductSingleResponse) {
                product.value = data.product
            }

            override fun onFailure() {
                productList.postValue(null)
            }
        }, token, lang, productId)
    }
}