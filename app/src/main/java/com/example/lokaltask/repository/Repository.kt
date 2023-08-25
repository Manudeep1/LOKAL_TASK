package com.example.lokaltask.repository

import com.example.lokaltask.api_interface.ProductApi
import javax.inject.Inject

/**
 * This repository is part of the MVVM pattern
 * MVVM pattern is used to separate business logic code, which help in maintenance of code
 * so MVVM works like REMOTE DATA SOURCE -> Repository -> ViewModel -> Views
 * This repo needs instance our api call
 */
class Repository @Inject constructor(private val productApi: ProductApi) {
     suspend fun getProductData() = productApi.getProductData()
}