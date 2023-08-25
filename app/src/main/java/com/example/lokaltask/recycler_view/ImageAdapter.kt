package com.example.lokaltask.recycler_view

import android.content.Context
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
 * I have used to show multiple product Image
 */

class ImageAdapter(private val context: Context, private val imageList: List<String>): RecyclerView.Adapter<ImageAdapter.ImageListViewHolder>() {

    inner class ImageListViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
       val image = ItemView.findViewById<ImageView>(R.id.iv_product_images)


    }

    /**
     * This method is like inflating party balloons with air
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.image_list_item, parent, false)
        return ImageListViewHolder(view)
    }

    /**
     * this will return our size of list, which will tell how many item will be displayed by recycler view
     */
    override fun getItemCount(): Int {
        return imageList.size
    }

    /**
     * This method is like sticking those inflated balloons on desired place
     */
    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        // Glide is used for image loading form url
        Glide.with(context).load(imageList[position]).apply(
            RequestOptions().transform(
                RoundedCorners(8)
            )
        ).centerCrop().into(holder.image)

    }
}