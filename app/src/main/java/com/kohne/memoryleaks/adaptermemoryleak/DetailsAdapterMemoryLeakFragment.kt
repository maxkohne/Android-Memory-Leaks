package com.kohne.memoryleaks.adaptermemoryleak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.databinding.DetailsMemoryLeakAdapterFragmentBinding

class DetailsAdapterMemoryLeakFragment : Fragment() {

    private lateinit var binding: DetailsMemoryLeakAdapterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsMemoryLeakAdapterFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        fun newInstance() = DetailsAdapterMemoryLeakFragment()
    }
}