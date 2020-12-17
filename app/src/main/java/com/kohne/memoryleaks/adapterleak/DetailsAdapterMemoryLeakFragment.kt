package com.kohne.memoryleaks.adapterleak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.databinding.DetailsAdapterMemoryLeakFragmentBinding

class DetailsAdapterMemoryLeakFragment : Fragment() {

    private lateinit var binding: DetailsAdapterMemoryLeakFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsAdapterMemoryLeakFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = DetailsAdapterMemoryLeakFragment()
    }
}