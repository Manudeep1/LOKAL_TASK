package com.example.lokaltask.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lokaltask.data_model.DataModel
import com.example.lokaltask.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * This View Model has all the data that are required be the apps
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _userData: MutableLiveData<DataModel> = MutableLiveData()
    val userData: LiveData<DataModel> = _userData
     suspend fun getProduct()
    {

        //val response  = repository.getProductData()
        /*
        response.enqueue(object : Callback<DataModel?>{
            override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {
                if (response.isSuccessful){
                    val responseData = response.body()!!
                    Log.d("My response","Data: $responseData ")
                    _userData.postValue(responseData)
                }
            }

            override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                Log.e("Error",t.message.toString())
            }

        })*/
        try {
            val response = repository.getProductData()
            _userData.postValue(response)
        }
        catch (e : Exception){
            Log.e("Network Error", e.message.toString())

        }
    }
}