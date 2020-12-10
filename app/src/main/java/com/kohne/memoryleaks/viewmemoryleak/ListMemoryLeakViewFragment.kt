package com.kohne.memoryleaks.viewmemoryleak

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.MainNavigationListener
import com.kohne.memoryleaks.R
import com.kohne.memoryleaks.databinding.ListMemoryLeakViewFragmentBinding

internal class ListMemoryLeakViewFragment : Fragment(R.layout.list_memory_leak_view_fragment),
    ListMemoryLeakViewAdapter.OnListMemoryLeakItemClickedListener {

    // This will leak both the binding and the adapter
    private lateinit var binding: ListMemoryLeakViewFragmentBinding
    private lateinit var adapter: ListMemoryLeakViewAdapter

    private var navigationListener: MainNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as MainNavigationListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ListMemoryLeakViewAdapter(listener = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListMemoryLeakViewFragmentBinding.inflate(inflater, container, false)

        binding.listMemoryLeakRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        navigationListener = null
    }

    override fun onListItemClicked(data: String) {
        navigationListener?.launchFragment(DetailsMemoryLeakViewFragment.newInstance())
    }

    companion object {
        fun newInstance() = ListMemoryLeakViewFragment()
    }
}