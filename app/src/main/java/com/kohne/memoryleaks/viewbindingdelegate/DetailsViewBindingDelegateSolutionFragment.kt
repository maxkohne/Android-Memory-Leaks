package com.kohne.memoryleaks.viewbindingdelegate

import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.R
import com.kohne.memoryleaks.databinding.DetailsViewBindingDelegateSolutionFragmentBinding
import com.kohne.memoryleaks.viewBinding

internal class DetailsViewBindingDelegateSolutionFragment : Fragment(R.layout.details_view_binding_delegate_solution_fragment) {
    companion object {
        fun newInstance() = DetailsViewBindingDelegateSolutionFragment()
    }

    private val binding by viewBinding(DetailsViewBindingDelegateSolutionFragmentBinding::bind)
}