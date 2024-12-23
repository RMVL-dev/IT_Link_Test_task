package com.example.itlinktesttask.ui.gridScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itlinktesttask.databinding.ImageItemViewHolderBinding

class GridImagesAdapter: RecyclerView.Adapter<ImageHolder>() {

    var imageListUrls = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder =
        ImageHolder(
            binding = ImageItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int =
        imageListUrls.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(
            imageUrl = imageListUrls[position]
        )
    }
}