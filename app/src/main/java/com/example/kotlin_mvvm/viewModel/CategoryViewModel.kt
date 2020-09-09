package com.example.task.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.model.Category
import com.example.task.model.CategoryListResponse
import com.example.task.repository.CategoryRepository
import com.example.task.repository.OperationCallback

class CategoryViewModel : ViewModel() {
    private val categoryRepository: CategoryRepository = CategoryRepository()
    var categoryList = MutableLiveData<List<Category>>()

    init {
        categoryList.value = listOf()
    }

    fun getTopCategories(token: String, lang: String) {
        categoryRepository.getTopCategories(object : OperationCallback<CategoryListResponse> {
            override fun onSuccess(data: CategoryListResponse) {
                categoryList.value = data.categories
            }

            override fun onFailure() {
                categoryList.postValue(null)
            }
        }, token, lang)
    }
}