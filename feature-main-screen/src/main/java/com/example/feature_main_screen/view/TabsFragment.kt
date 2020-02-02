package com.example.feature_main_screen.view


import android.os.Bundle
import android.os.Handler
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
                R.id.navigation_photos -> {
                    onPhotosTabSelected()
                }
                R.id.navigation_profile -> {
                    onProfileTabSelected()
                }
            }
            true
        }

        if (savedInstanceState == null) Handler().post { onPhotosTabSelected() }
    }

    private fun onProfileTabSelected() {
        navigator?.profileTab()
        tabPhotosContainer.visibility = View.GONE
        tabsProfileContainer.visibility = View.VISIBLE
    }

    private fun onPhotosTabSelected() {
        navigator?.photosTab()
        tabPhotosContainer.visibility = View.VISIBLE
        tabsProfileContainer.visibility = View.GONE
    }

    companion object {
        fun newInstance() = TabsFragment()
    }
}
