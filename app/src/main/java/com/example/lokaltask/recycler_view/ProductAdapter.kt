package com.example.lokaltask.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lokaltask.MainActivity
import com.example.lokaltask.ProductFragment
import com.example.lokaltask.R
import com.example.lokaltask.data_model.Product
/**
 * This class responsible for creating our list view items for recycler view
 * Though we recycler view is more efficient than list view, so recycler view has its own unique
 * methods to create views and place it in desired containers
 */
class ProductAdapter(private val context: MainActivity, private val productList: List<Product>): RecyclerView.Adapter<ProductAdapter.ProductListViewHolder>() {

    class ProductListViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val ivIcon = ItemView.findViewById<ImageView>(R.id.iv_icon)
        val tvProduct = ItemView.findViewById<TextView>(R.id.tv_product_name)
        val tvCatagory = ItemView.findViewById<TextView>(R.id.tv_catagory)
        val tvPrice = ItemView.findViewById<TextView>(R.id.tv_price)
        val tvDiscount = ItemView.findViewById<TextView>(R.id.tv_discount)


    }
    /**
     * This method is like inflating party balloons with air
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item_list,parent,false)
        return ProductListViewHolder(view)
    }

    /**
     * this will return our size of list, which will tell how many item will be displayed by recycler view
     */
    override fun getItemCount(): Int {
        return productList.size
    }
    /**
     * This method is like sticking those inflated balloons on desired place
     */
    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        Glide.with(context).load(productList[position].thumbnail).apply(
            RequestOptions().transform(
            RoundedCorners(8)
        )).centerCrop().into(holder.ivIcon)
        val catagory = "Catagory : " + productList[position].category.uppercase()
        val price = "₹ "+ productList[position].price.toString()
        val discount = productList[position].discountPercentage.toString() + "%"
        holder.apply {
            tvCatagory.text = catagory
            tvProduct.text = productList[position].title
            tvPrice.text = price
            tvDiscount.text = discount
            holder.itemView.setOnClickListener {
                // Handle item click here
                context.listItemClicked(position)
                Toast.makeText(holder.itemView.context, "Clicked item : $position ", Toast.LENGTH_SHORT).show()

            }

        }
    }


}