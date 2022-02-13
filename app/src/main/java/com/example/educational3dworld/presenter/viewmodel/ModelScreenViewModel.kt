package com.example.educational3dworld.presenter.viewmodel

import com.example.educational3dworld.data.models.ModelData
import kotlinx.coroutines.flow.Flow

interface ModelScreenViewModel {

    val modelFlow: Flow<ModelData>
    fun getObjectUrl(type:Int,id:Long)
}