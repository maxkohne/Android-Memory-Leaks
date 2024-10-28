package com.kohne.memoryleaks

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.kohne.memoryleaks.databinding.MainActivityBinding

internal class MainActivity : AppCompatActivity(), MainNavigationListener {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)

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