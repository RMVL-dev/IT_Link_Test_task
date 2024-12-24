package com.example.itlinktesttask.ui.imageScreen

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.itlinktesttask.R
import com.example.itlinktesttask.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val brokenImage =
            ResourcesCompat.getDrawable(requireContext().resources, R.drawable.ic_broken_image, requireContext().theme)

        val placeHolder =
            ResourcesCompat.getDrawable(requireContext().resources, R.drawable.ic_image_placeholder, requireContext().theme)

        if (requireContext().resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES) {
            brokenImage?.let { DrawableCompat.setTint(it, Color.WHITE) }
            placeHolder?.let { DrawableCompat.setTint(it, Color.WHITE) }
        }

        val options: RequestOptions = RequestOptions()
            .dontAnimate()
            .centerInside()
            .placeholder(placeHolder) //Use a placeholder image
            .error(brokenImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)

        Glide.with(binding.ivImage)
            .load(arguments?.getString(BUNDLE_ID))
            .apply(options)
            .into(binding.ivImage)

        binding.ibBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.ivImage.setOnClickListener {
            binding.toolbar.isVisible = !binding.toolbar.isVisible
            binding.divider.isVisible = !binding.divider.isVisible
        }

    }

    companion object{
        const val BUNDLE_ID = "imageUrl"
    }
}