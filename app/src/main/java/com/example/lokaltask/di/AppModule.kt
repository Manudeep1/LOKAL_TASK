package com.example.lokaltask.di

import com.example.lokaltask.api_interface.ProductApi
import com.example.lokaltask.helper.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * We are providing dependency, we know ProductApi is Interface to provide dependency to required
     * dependent points
     */
    var retrofitService : ProductApi? = null
    @Provides
    fun provideBaseURL() = Constants.BASE_URL
    @Provides
    @Singleton
    fun getInstance(BASE_URL : String): ProductApi {
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
}