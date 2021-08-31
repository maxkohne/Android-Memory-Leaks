package com.kohne.memoryleaks.viewbindingleak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.MainNavigationListener
import com.kohne.memoryleaks.R
import com.kohne.memoryleaks.databinding.ListViewBindingMemoryLeakFragmentBinding

internal class ListViewBindingMemoryLeakFragment : Fragment(R.layout.list_view_binding_memory_leak_fragment),
    ListViewBindingMemoryLeakAdapter.OnListMemoryLeakItemClickedListener {

    companion object {
        fun newInstance() = ListViewBindingMemoryLeakFragment()
    }

    // This will leak both the binding and the adapter
    private lateinit var binding: ListViewBindingMemoryLeakFragmentBinding
    private lateinit var adapter: ListViewBindingMemoryLeakAdapter

    private val navigationListener: MainNavigationListener get() = requireContext() as MainNavigationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ListViewBindingMemoryLeakAdapter(listener = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListViewBindingMemoryLeakFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onListItemClicked(data: String) {
        navigationListener.launchFragment(DetailsViewBindingMemoryLeakFragment.newInstance())
    }
}