package com.kohne.memoryleaks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.adapterleak.ListAdapterMemoryLeakFragment
import com.kohne.memoryleaks.databinding.MainFragmentBinding
import com.kohne.memoryleaks.lazybindingleak.ListLazyBindingMemoryLeakFragment
import com.kohne.memoryleaks.viewbindingdelegate.ListViewBindingDelegateSolutionFragment
import com.kohne.memoryleaks.viewbindingleak.ListViewBindingMemoryLeakFragment

internal class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val binding by viewBinding(MainFragmentBinding::bind)
    private val navigationListener: MainNavigationListener get() = requireContext() as MainNavigationListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainButtonViewMemoryLeak.setOnClickListener {
            navigationListener.launchFragment(ListViewBindingMemoryLeakFragment.newInstance())
        }

        binding.mainButtonLazyMemoryLeak.setOnClickListener {
            navigationListener.launchFragment(ListLazyBindingMemoryLeakFragment.newInstance())
        }

        binding.mainButtonAdapterMemoryLeak.setOnClickListener {
            navigationListener.launchFragment(ListAdapterMemoryLeakFragment.newInstance())
        }

        binding.mainButtonViewBinding.setOnClickListener {
            navigationListener.launchFragment(ListViewBindingDelegateSolutionFragment.newInstance())
        }
    }
}