package com.kohne.memoryleaks.viewbindingdelegate

import androidx.fragment.app.Fragment
import com.kohne.memoryleaks.R
import com.kohne.memoryleaks.databinding.DetailsViewBindingFragmentBinding
import com.kohne.memoryleaks.viewBinding

internal class DetailsViewBindingDelegateSolutionFragment : Fragment(R.layout.details_view_binding_delegate_solution_fragment) {

    private val binding by viewBinding(DetailsViewBindingFragmentBinding::bind)

    companion object {
        fun newInstance() = DetailsViewBindingDelegateSolutionFragment()
    }
}