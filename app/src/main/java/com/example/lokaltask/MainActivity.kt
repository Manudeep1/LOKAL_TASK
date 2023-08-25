package com.example.lokaltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokaltask.data_model.Product
import com.example.lokaltask.databinding.ActivityMainBinding
import com.example.lokaltask.recycler_view.ProductAdapter
import com.example.lokaltask.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Creating variable
    private lateinit var binding : ActivityMainBinding // View binding
    private lateinit var viewModel : MainViewModel
    private var data : List<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // binding our recycler view
        binding.rvProductList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        //val retrofitService : ProductApi = ProductApi.getInstance()

        viewModel = ViewModelProvider(this)[ MainViewModel::class.java]

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getProduct()
        }
        viewModel.userData.observe(this) {
            binding.loadingAnni.visibility = View.VISIBLE
            Log.d("Main Activity Data", "Data : $it")
            binding.rvProductList.adapter = ProductAdapter(this@MainActivity,it.products)
            data = it.products
            binding.loadingAnni.visibility = View.INVISIBLE
        }

    }
    fun listItemClicked(postion : Int) {
        val frag = ProductFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_container, frag)
            .addToBackStack(null).commit()
        /**
         * Here we are sending the required data to our fragment,
         * there could be many ways to send the data, but here I have used Bundle()
         * We could have also used HASHMAP
         */
        val bundle = Bundle()
        bundle.apply {
            putString("title", data!![postion].title)
            putString("brand", data!![postion].brand)
            putString("catagory", data!![postion].category)
            putDouble("discount", data!![postion].discountPercentage)
            putInt("price", data!![postion].price)
            putDouble("rating",data!![postion].rating)
            putString("desc",data!![postion].description)
            putStringArrayList("images",ArrayList<String>(data!![postion].images))
        }

        frag.arguments = bundle
        binding.llRv.visibility = View.INVISIBLE
        binding.toolbar.visibility = View.INVISIBLE

    }
}