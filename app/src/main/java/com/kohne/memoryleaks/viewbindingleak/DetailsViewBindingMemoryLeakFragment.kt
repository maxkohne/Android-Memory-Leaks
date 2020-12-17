package com.kohne.memoryleaks.viewbindingleak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.databinding.DetailsViewBindingMemoryLeakFragmentBinding

internal class DetailsViewBindingMemoryLeakFragment : Fragment() {

    private lateinit var binding: DetailsViewBindingMemoryLeakFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsViewBindingMemoryLeakFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = DetailsViewBindingMemoryLeakFragment()
    }
}