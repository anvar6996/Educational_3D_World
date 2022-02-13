package com.example.educational3dworld.domain.repository

import com.example.educational3dworld.data.models.CollectionData
import com.example.educational3dworld.data.models.ObjectData
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    var collectionsType: Map<Int, String>

    fun successLoadListener(block: () -> Unit)

    fun getObjectsByType(type: Int): Flow<List<ObjectData>>

}