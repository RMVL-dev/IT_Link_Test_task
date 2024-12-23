package com.example.itlinktesttask.ui.gridScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.itlinktesttask.databinding.FragmentGridImagesBinding
import dagger.hilt.android.AndroidEntryPoint


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

}