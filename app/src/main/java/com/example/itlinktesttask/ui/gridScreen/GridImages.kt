package com.example.itlinktesttask.ui.gridScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.itlinktesttask.databinding.FragmentGridImagesBinding
import com.example.itlinktesttask.ui.gridScreen.adapter.GridImagesAdapter
import com.example.itlinktesttask.utils.collectFlowWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode


@AndroidEntryPoint
class GridImages : Fragment() {

    private var _binding:FragmentGridImagesBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: ImagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridImagesBinding.inflate(inflater,container, false)
        viewModel.getFile()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = GridImagesAdapter()
        binding.rvPreviewImages.adapter = adapter

        collectFlowWhenStarted(viewModel.images){ imageUrls ->
            adapter.imageListUrls = imageUrls
            adapter.notifyDataSetChanged()
        }

        val width = resources.displayMetrics.widthPixels / requireContext().resources.displayMetrics.density
        val spanCount = (width/100).toBigDecimal().setScale(1,RoundingMode.DOWN).toInt()
        Log.d("SpanCount", "width - $width spanCount - $spanCount calculating ${(width/100).toBigDecimal()}")
        binding.rvPreviewImages.setLayoutManager(GridLayoutManager(requireContext(), spanCount))

    }

}