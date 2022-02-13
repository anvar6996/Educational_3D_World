package com.example.educational3dworld.presenter.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.data.models.CollectionData
import com.example.educational3dworld.databinding.PageMainBinding
import com.example.educational3dworld.presenter.adapters.CollectionAdapter
import com.example.educational3dworld.presenter.viewmodel.MainPageViewModel
import com.example.educational3dworld.presenter.viewmodel.viewmodelimpl.MainPageViewModelImpl
import com.example.educational3dworld.utils.scope
import com.example.educational3dworld.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
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


        showToast("lala")
        val list = ArrayList<CollectionData>()
        list.add(
            CollectionData(
                1,
                "https://thedispatch.blob.core.windows.net/thedispatchimages/2021/09/History.jpg"
            )
        )
        list.add(
            CollectionData(
                2,
                "https://img3.goodfon.ru/wallpaper/nbig/d/f2/colorful-geometry-shapes-7654.jpg"
            )
        )
        list.add(
            CollectionData(
                3,
                "https://leverageedu.com/blog/wp-content/uploads/2020/01/Branches-of-Zoology.png"
            )
        )
        list.add(
            CollectionData(
                4,
                "https://skyandtelescope.org/wp-content/uploads/Solar-system-NASA_S-630x338.jpg"
            )
        )
        list.add(
            CollectionData(
                5,
                "http://musicedhighlights.files.wordpress.com/2011/03/2010-clip-art.gif"
            )
        )
        viewModel.getModels()
        adaptetColleaction.submitList(list)

        viewModel.successGetModelsFlow.onEach {
            showToast(it.size.toString())
            adaptetColleaction.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)
    }
}