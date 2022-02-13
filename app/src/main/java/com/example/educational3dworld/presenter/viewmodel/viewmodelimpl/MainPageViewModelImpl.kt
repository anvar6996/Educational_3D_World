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


    init {
        repository.getObjectsByType(1)
        progressFlow.tryEmit(true)
        repository.successLoadListener {
            progressFlow.tryEmit(false)

            getModels()
        }
    }

    override fun getModels() {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }

        viewModelScope.launch {
            successGetListFlow.emit(repository.objectsList)
        }
    }

    override fun getModelsData(type: Int) {

    }

    //
//    override fun getModelsData(type: Int) {
//        if (!isConnected()) {
//            viewModelScope.launch {
//                errorFlow.emit("Internet bilan muammo bo'ldi")
//            }
//            return
//        }
//
//        viewModelScope.launch {
//            repository.getObjectsByType(type).onEach {
//                successGetListFlow.emit(it as ArrayList<ObjectData>)
//            }.launchIn(viewModelScope)
//
//        }
//    }
    override fun getObjects(type: Int) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            successGetListFlow.emit(repository.objectsList)
        }

    }
}

