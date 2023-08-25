package com.example.lokaltask.api_interface

import com.example.lokaltask.data_model.DataModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * This is interface used for making network calls,
 * using @GET request the request will be send to desired add in return we will get object of
 * Data Model
 */
interface ProductApi {
    @GET("products")
     suspend fun getProductData(): DataModel


    /*companion object{
        var retrofitService : ProductApi? = null

        fun getInstance(): ProductApi{
            if(retrofitService == null)
            {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://dummyjson.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ProductApi::class.java)
            }
            return retrofitService!!
        }
    }*/
}