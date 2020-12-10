package com.kohne.memoryleaks

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.adaptermemoryleak.ListAdapterMemoryLeakFragment
import com.kohne.memoryleaks.databinding.MainFragmentBinding
import com.kohne.memoryleaks.viewbindingdelegate.ListViewBindingDelegateSolutionFragment
import com.kohne.memoryleaks.viewmemoryleak.ListMemoryLeakViewFragment

internal class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding by viewBinding(MainFragmentBinding::bind)
    private var navigationListener: MainNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationListener = context as MainNavigationListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainButtonViewMemoryLeak.setOnClickListener {
            navigationListener?.launchFragment(ListMemoryLeakViewFragment.newInstance())
        }

        binding.mainButtonAdapterMemoryLeak.setOnClickListener {
            navigationListener?.launchFragment(ListAdapterMemoryLeakFragment.newInstance())
        }

        binding.mainButtonViewBinding.setOnClickListener {
            navigationListener?.launchFragment(ListViewBindingDelegateSolutionFragment.newInstance())
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigationListener = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}