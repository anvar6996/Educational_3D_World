package com.example.educational3dworld.presenter.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.PageMainBinding
import com.example.educational3dworld.presenter.adapters.CollectionAdapter
import com.example.educational3dworld.presenter.viewmodel.MainPageViewModel
import com.example.educational3dworld.presenter.viewmodel.viewmodelimpl.MainPageViewModelImpl
import com.example.educational3dworld.utils.scope
import com.example.educational3dworld.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MainPage : Fragment(R.layout.page_main) {
    private val bind by viewBinding(PageMainBinding::bind)
    private val adaptetColleaction by lazy { CollectionAdapter() }
    private val viewModel: MainPageViewModel by viewModels<MainPageViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)
        recykler.adapter = adaptetColleaction
        recykler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel.successGetModelsFlow.onEach {
            adaptetColleaction.submitList(it)
        }
    }
}