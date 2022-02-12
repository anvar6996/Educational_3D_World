package com.example.educational3dworld.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.ScreenDrawerMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.educational3dworld.presenter.adapters.MainPageAdapters
import com.example.educational3dworld.utils.scope

@AndroidEntryPoint
class DrawerScreen : Fragment(R.layout.screen_drawer_main) {
    private val bind by viewBinding(ScreenDrawerMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)

        val mainPageAdapters = MainPageAdapters(childFragmentManager, lifecycle)
        include.pager.adapter = mainPageAdapters
        include.pager.isUserInputEnabled = false
        include.menu.setOnClickListener {
            drawerMenu.openDrawer(GravityCompat.START)
        }


        include.bottomNavigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    include.pager.setCurrentItem(0, true)
                }
                R.id.catalog -> {
                    include.pager.setCurrentItem(1, true)
                }
                R.id.QR -> {
                    include.pager.setCurrentItem(2, true)
                }
                R.id.calendar -> {
                    include.pager.setCurrentItem(3, true)
                }
                else -> {
                    include.pager.setCurrentItem(4, true)
                }
            }
            return@setOnItemSelectedListener true
        }
    }


}