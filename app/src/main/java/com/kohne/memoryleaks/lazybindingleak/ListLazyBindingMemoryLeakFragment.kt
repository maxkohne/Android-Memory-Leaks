package com.kohne.memoryleaks.lazybindingleak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.MainNavigationListener
import com.kohne.memoryleaks.R
import com.kohne.memoryleaks.databinding.ListViewBindingMemoryLeakFragmentBinding

internal class ListLazyBindingMemoryLeakFragment :
    Fragment(R.layout.list_view_binding_memory_leak_fragment),
    ListLazyBindingMemoryLeakAdapter.OnListMemoryLeakItemClickedListener {

    companion object {
        fun newInstance() = ListLazyBindingMemoryLeakFragment()
    }

    // This will leak the binding because, although we are lazily init'ing in onCreateView, it
    // will not be destroyed / nulled out in onDestroyView. Also another important thing to note
    // is that when returning to this list fragment from backing out of the details fragment, this
    // lazy property will not be recreated since it is not being called again after a new view
    // has been created. This causes the list to show a blank screen because it is loading the
    // adapter on the view from the initial call to onCreateView (that this lazy property is
    // holding onto) and not the view that is recreated the second time when returning from the
    // backstack.
    private val binding : ListViewBindingMemoryLeakFragmentBinding by lazy {
        ListViewBindingMemoryLeakFragmentBinding.bind(requireView())
    }

    private val navigationListener: MainNavigationListener get() = requireContext() as MainNavigationListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = ListLazyBindingMemoryLeakAdapter(listener = this)
    }

    override fun onListItemClicked(data: String) {
        navigationListener.launchFragment(DetailsLazyBindingMemoryLeakFragment.newInstance())
    }
}