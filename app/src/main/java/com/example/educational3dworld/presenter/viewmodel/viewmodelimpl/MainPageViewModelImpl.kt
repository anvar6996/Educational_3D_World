package com.example.educational3dworld.presenter.viewmodel.viewmodelimpl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educational3dworld.data.models.CollectionData
import com.example.educational3dworld.data.models.ObjectData
import com.example.educational3dworld.domain.repository.AppRepository
import com.example.educational3dworld.presenter.viewmodel.MainPageViewModel
import com.example.educational3dworld.utils.eventValueFlow
import com.example.educational3dworld.utils.isConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(),
    MainPageViewModel {

    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successGetModelsFlow = eventValueFlow<ArrayList<CollectionData>>()
    override val successGetListFlow = eventValueFlow<ArrayList<ObjectData>>()


    override fun getModels() {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }

        viewModelScope.launch {
            successGetModelsFlow.emit(repository.successLoadImage())
        }
    }

    override fun getObjects(type: Int) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }


        repository.getObjectsByType(type)
        progressFlow.tryEmit(true)
        repository.successLoadListener {
            viewModelScope.launch(Dispatchers.IO) {
                progressFlow.tryEmit(false)
                successGetListFlow.emit(repository.objectsList)

            }
        }

    }
}

