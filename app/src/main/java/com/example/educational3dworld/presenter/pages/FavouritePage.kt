package com.example.educational3dworld.presenter.pages

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.PageFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritePage : Fragment(R.layout.page_favourites) {
    private val bind by viewBinding(PageFavouritesBinding::bind)
}