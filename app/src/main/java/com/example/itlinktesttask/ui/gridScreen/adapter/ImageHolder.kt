package com.example.itlinktesttask.ui.gridScreen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itlinktesttask.R
import com.example.itlinktesttask.databinding.ImageItemViewHolderBinding

class ImageHolder(
    private val binding:ImageItemViewHolderBinding
):RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl:String){
        Glide.with(binding.ivImage)
            .load(imageUrl)
            .error(R.drawable.broken_image)
            .into(binding.ivImage)
    }

}