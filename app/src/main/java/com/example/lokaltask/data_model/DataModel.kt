package com.example.lokaltask.data_model

/**
 * This is our data model class which defines the format of data we will be get from api
 */
data class DataModel(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)