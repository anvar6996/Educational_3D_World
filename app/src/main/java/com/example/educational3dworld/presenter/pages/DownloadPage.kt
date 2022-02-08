package com.example.educational3dworld.presenter.pages

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.PageDownloadsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadPage : Fragment(R.layout.page_downloads) {
    private val bind by viewBinding(PageDownloadsBinding::bind)
}