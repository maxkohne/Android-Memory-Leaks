package com.kohne.memoryleaks.adapterleak

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.MainNavigationListener
import com.kohne.memoryleaks.R
import com.kohne.memoryleaks.databinding.ListAdapterMemoryLeakFragmentBinding
import com.kohne.memoryleaks.viewBinding

internal class ListAdapterMemoryLeakFragment : Fragment(R.layout.list_adapter_memory_leak_fragment),
    ListAdapterMemoryLeakAdapter.OnListMemoryLeakItemClickedListener {

    // This will only leak the adapter but not the binding
    private val binding by viewBinding(ListAdapterMemoryLeakFragmentBinding::bind)
    private lateinit var adapter: ListAdapterMemoryLeakAdapter

    private var navigationListener: MainNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as MainNavigationListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ListAdapterMemoryLeakAdapter(listener = this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        navigationListener = null
    }

    override fun onListItemClicked(data: String) {
        navigationListener?.launchFragment(DetailsAdapterMemoryLeakFragment.newInstance())
    }

    companion object {
        fun newInstance() = ListAdapterMemoryLeakFragment()
    }
}