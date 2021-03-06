package com.example.educational3dworld.presenter.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educational3dworld.R
import com.example.educational3dworld.databinding.PageMainBinding
import com.example.educational3dworld.presenter.adapters.CollectionAdapter
import com.example.educational3dworld.presenter.adapters.ObjectAdapter
import com.example.educational3dworld.presenter.screens.DrawerScreenDirections
import com.example.educational3dworld.presenter.screens.MainScreen
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
    private val objectAdapter by lazy { ObjectAdapter() }
    private val viewModel: MainPageViewModel by viewModels<MainPageViewModelImpl>()
    private val navController by lazy{findNavController()}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)
        recykler.adapter = adaptetColleaction
        recykler.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        viewModel.getModels()



        adaptetColleaction.setListener {
            viewModel.getObjects(it + 1)
        }

        objectAdapter.setListener { type, id ->
            findNavController().navigate(DrawerScreenDirections.actionDrawerScreenToModelScreen(id.toLong(),type))
        }
        viewModel.successGetListFlow.onEach {
            showToast(it.size.toString() + " firebase")
            recykler.adapter = objectAdapter
            recykler.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            objectAdapter.submitList(it)
//            recykler.layoutAnimation.notify()
        }.launchIn(lifecycleScope)


        viewModel.progressFlow.onEach {
            if (it) {
                progress.show()
            } else {
                progress.hide()
            }
        }.launchIn(lifecycleScope)

        viewModel.successGetModelsFlow.onEach {
            showToast(it.size.toString())
            adaptetColleaction.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.errorFlow.onEach {
            showToast(it)

        }.launchIn(lifecycleScope)
    }
}