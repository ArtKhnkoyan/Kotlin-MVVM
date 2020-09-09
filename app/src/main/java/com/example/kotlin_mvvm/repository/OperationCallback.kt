package com.example.task.repository

interface OperationCallback<T> {

    fun onSuccess(data: T)
    fun onFailure()
}