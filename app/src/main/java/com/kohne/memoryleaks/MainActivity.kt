package com.kohne.memoryleaks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

internal class MainActivity : AppCompatActivity(), MainNavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            launchFragment(MainFragment.newInstance(), false)
        }
    }

    override fun launchFragment(fragment: Fragment) {
        launchFragment(fragment, true)
    }

    private fun launchFragment(fragment: Fragment, addToBackStack: Boolean) {
        supportFragmentManager.commit {
            replace(R.id.main_fragment_container_view, fragment)
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
    }
}

interface MainNavigationListener {
    fun launchFragment(fragment: Fragment)
}