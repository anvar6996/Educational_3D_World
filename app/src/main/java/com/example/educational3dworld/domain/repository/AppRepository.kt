package com.example.educational3dworld.domain.repository

import com.example.educational3dworld.data.models.CollectionData
import com.example.educational3dworld.data.models.ObjectData
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    var collectionsType: Map<Int, String>
//    var collectionImage: ArrayList<CollectionData>

    fun successLoadListener(block: () -> Unit)
    fun successLoadImage(): Flow<ArrayList<CollectionData>>

    fun getObjectsByType(type: Int): Flow<List<ObjectData>>

}