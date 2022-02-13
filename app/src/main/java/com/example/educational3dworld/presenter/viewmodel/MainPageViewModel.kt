package com.example.educational3dworld.presenter.viewmodel

import com.example.educational3dworld.data.models.CollectionData
import kotlinx.coroutines.flow.Flow

interface MainPageViewModel {
    val errorFlow: Flow<String>
    val progressFlow: Flow<Boolean>
    val successGetModelsFlow: Flow<ArrayList<CollectionData>>

    fun getModels()

}