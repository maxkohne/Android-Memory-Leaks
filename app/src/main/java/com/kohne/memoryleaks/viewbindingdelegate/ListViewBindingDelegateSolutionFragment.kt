package com.kohne.memoryleaks.viewbindingdelegate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.MainNavigationListener
import com.kohne.memoryleaks.R
import com.kohne.memoryleaks.databinding.ListViewBindingDelegateSolutionFragmentBinding
import com.kohne.memoryleaks.viewBinding

internal class ListViewBindingDelegateSolutionFragment :
    Fragment(R.layout.list_view_binding_delegate_solution_fragment),
    ListViewBindingDelegateSolutionAdapter.OnListViewBindingItemClickedListener {

    private val binding by viewBinding(ListViewBindingDelegateSolutionFragmentBinding::bind)

    private val navigationListener: MainNavigationListener get() = requireContext() as MainNavigationListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listViewBindingRecyclerView.adapter =
            ListViewBindingDelegateSolutionAdapter(listener = this)
    }

    override fun onListItemClicked(data: String) {
        navigationListener.launchFragment(DetailsViewBindingDelegateSolutionFragment.newInstance())
    }

    companion object {
        fun newInstance() = ListViewBindingDelegateSolutionFragment()
    }
}