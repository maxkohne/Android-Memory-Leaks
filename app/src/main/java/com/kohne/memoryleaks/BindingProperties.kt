package com.kohne.memoryleaks

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Inspired by:
 *  - https://medium.com/default-to-open/handling-lifecycle-with-view-binding-in-fragments-a7f237c56832
 *  - https://medium.com/@Zhuinden/simple-one-liner-viewbinding-in-fragments-and-activities-with-kotlin-961430c6c07c
 */
fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
): ReadOnlyProperty<Fragment, T> = object : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    init {
        // Observe on Fragment's view's lifecycle
        viewLifecycleOwnerLiveData.observe(this@viewBinding, Observer { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                // Since we are observing on the view's lifecycle, this is
                // the equivalent of the Fragment's onDestroyView() method
                override fun onDestroy(owner: LifecycleOwner) {
                    // If the binding is a data binding then clean up
                    (binding as? ViewDataBinding)?.unbind()
                    binding = null
                }
            })
        })

        // Observe on fragment's lifecycle
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                // This enforces that a view must be created either by passing into the content layout
                // id to the Fragment constructor or by overriding onCreateView() in the Fragment
                view ?: error("You must either pass in the layout ID into ${this@viewBinding.javaClass.simpleName}'s constructor or inflate a view in onCreateView()")
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        // Bail early if the binding has already been created
        binding?.let { return it }

        // Make sure we are in a valid state
        val viewLifecycleOwner = try {
            thisRef.viewLifecycleOwner
        } catch (e: IllegalStateException) {
            error("Should not attempt to get bindings when Fragment views haven't been created yet. The fragment has not called onCreateView() at this point.")
        }
        if (!viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Should not attempt to get bindings when Fragment views are destroyed. The fragment has already called onDestroyView() at this point.")
        }

        // Create the binding
        return viewBindingFactory(thisRef.requireView()).also { viewBinding ->
            // If the binding is a data binding then set the view lifecycle owner
            if (viewBinding is ViewDataBinding) {
                viewBinding.lifecycleOwner = viewLifecycleOwner
            }

            this.binding = viewBinding
        }
    }
}