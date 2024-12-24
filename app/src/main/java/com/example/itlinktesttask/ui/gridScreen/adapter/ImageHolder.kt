package com.example.itlinktesttask.ui.gridScreen.adapter

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.itlinktesttask.R
import com.example.itlinktesttask.databinding.ImageItemViewHolderBinding


class ImageHolder(
    private val binding:ImageItemViewHolderBinding,
    private val context: Context
):RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl:String){

        val brokenImage =
            ResourcesCompat.getDrawable(context.resources,R.drawable.ic_broken_image, context.theme)

        val placeHolder =
            ResourcesCompat.getDrawable(context.resources,R.drawable.ic_image_placeholder, context.theme)

        if (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES) {
            brokenImage?.let { DrawableCompat.setTint(it, Color.WHITE) }
            placeHolder?.let { DrawableCompat.setTint(it, Color.WHITE) }
        }

        val options: RequestOptions = RequestOptions()
            .dontAnimate()
            .centerCrop()
            .placeholder(placeHolder) //Use a placeholder image
            .error(brokenImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)

        Glide.with(binding.ivImage)
            .load(imageUrl)
            .apply(options)
            .into(binding.ivImage)
    }

}