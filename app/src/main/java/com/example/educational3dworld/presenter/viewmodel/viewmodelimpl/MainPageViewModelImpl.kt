package com.example.educational3dworld.presenter.viewmodel.viewmodelimpl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.educational3dworld.data.models.ObjectData
import com.example.educational3dworld.domain.repository.AppRepository
import com.example.educational3dworld.presenter.viewmodel.MainPageViewModel
import com.example.educational3dworld.utils.eventValueFlow
import com.example.educational3dworld.utils.isConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModelImpl @Inject constructor(
    private val repository: AppRepository
) :
    ViewModel(),
    MainPageViewModel {
    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successGetModelsFlow = eventValueFlow<ArrayList<ObjectData>>()


    override fun getModels() {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }

        viewModelScope.launch {
            progressFlow.emit(true)
        }

        repository.getObjectsByType(1).onEach { value ->
            progressFlow.emit(false)
            successGetModelsFlow.emit(value as ArrayList<ObjectData>)
        }.launchIn(viewModelScope)
    }


}