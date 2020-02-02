package com.example.feature_main_screen.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.mvi.view.BaseNavigationFragment
import com.example.feature_main_screen.R
import kotlinx.android.synthetic.main.fragment_tabs.*

class TabsFragment : BaseNavigationFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_photos -> navigator?.photosTab()
                R.id.navigation_profile -> navigator?.profileTab()
            }
            true
        }
    }

    companion object {
        fun newInstance() = TabsFragment()
    }
}
