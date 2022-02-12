package com.example.educational3dworld.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.ScreenSplashBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val bind by viewBinding(ScreenSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}