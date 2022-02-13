package com.example.educational3dworld.presenter.viewmodel.viewmodelimpl

import androidx.lifecycle.ViewModel
import com.example.educational3dworld.data.models.ModelData
import com.example.educational3dworld.domain.repository.AppRepository
import com.example.educational3dworld.presenter.viewmodel.ModelScreenViewModel
import com.example.educational3dworld.utils.eventValueFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class ModelScreenViewModelImpl @Inject constructor(
    private val repository: AppRepository
) :ViewModel(), ModelScreenViewModel {

    init {

    }

    override val modelFlow = eventValueFlow<ModelData>()

    override fun getObjectUrl(type: Int, id: Long) {
       repository.getModelById(id,type)

        repository.successLoadListener {
            repository.modelById?.let { modelFlow.tryEmit(it) }
        }
    }


}