package com.example.educational3dworld.presenter.viewmodel

import com.example.educational3dworld.data.models.CollectionData
import com.example.educational3dworld.data.models.ObjectData
import kotlinx.coroutines.flow.Flow

interface MainPageViewModel {
    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successGetModelsFlow: Flow<ArrayList<CollectionData>>
    val successGetListFlow: Flow<ArrayList<ObjectData>>

    fun getModels()
    fun getModelsData(type: Int)
    fun getObjects(type:Int)

}