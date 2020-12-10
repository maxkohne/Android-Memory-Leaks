package com.kohne.memoryleaks.viewmemoryleak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.databinding.DetailsViewMemoryLeakFragmentBinding

internal class DetailsMemoryLeakViewFragment : Fragment() {

    private lateinit var binding: DetailsViewMemoryLeakFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsViewMemoryLeakFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = DetailsMemoryLeakViewFragment()
    }
}