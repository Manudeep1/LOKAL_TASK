package com.example.lokaltask

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokaltask.databinding.FragmentProductBinding
import com.example.lokaltask.recycler_view.ImageAdapter

class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater,container,false)

        val view = binding.root
        val rating = arguments?.getDouble("rating").toString() + " ⭐"
        val dealPercentage = arguments?.getDouble("discount").toString() + "% Off"
        val imageList = arguments?.getStringArrayList("images")
        val price = "₹" + arguments?.getInt("price").toString()
        //binding all the views
        binding.apply {
            tvTitle.text = arguments?.getString("title")
            tvBrand.text = arguments?.getString("brand")
            tvCatatgory.text = arguments?.getString("catagory")?.uppercase()
            tvDeal.text = dealPercentage
            tvSelling.text = price
            tvRating.text = rating
            tvDescription.text = arguments?.getString("desc")
        }

        binding.rvProductImage.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        if (imageList != null) {
            binding.rvProductImage.adapter = ImageAdapter(context = requireContext(),imageList.toList())
        }
        // This will handle our on back pressed btn task
        val callback = object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {
                val i = Intent( activity ,MainActivity::class.java)
                activity?.startActivity(i)
                requireActivity().finish()
            }
        }
        binding.ivBackPressed.setOnClickListener {
            val i = Intent( activity ,MainActivity::class.java)
            activity?.startActivity(i)
            requireActivity().finish()
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}