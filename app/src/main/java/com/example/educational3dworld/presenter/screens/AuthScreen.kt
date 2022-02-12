package com.example.educational3dworld.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.ScreenAuthBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthScreen : Fragment(R.layout.screen_auth) {
    private val bind by viewBinding(ScreenAuthBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}