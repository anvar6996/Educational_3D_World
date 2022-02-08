package com.example.educational3dworld.presenter.pages

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.PageMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPage : Fragment(R.layout.page_main) {
    private val bind by viewBinding(PageMainBinding::bind)
}