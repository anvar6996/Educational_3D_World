package com.example.educational3dworld.presenter.pages

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.PageProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilePage : Fragment(R.layout.page_profile) {
    private val bind by viewBinding(PageProfileBinding::bind)
}