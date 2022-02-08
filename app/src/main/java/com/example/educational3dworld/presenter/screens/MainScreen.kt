package com.example.educational3dworld.presenter.screens

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding

import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.ScreenMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val bind by viewBinding(ScreenMainBinding::bind)
}