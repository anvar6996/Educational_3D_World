package com.example.educational3dworld.domain.repository

import com.example.educational3dworld.data.models.ObjectData

interface AppRepository {
    var collectionsType:Map<Int,String>

    fun successLoadListener(block : () -> Unit)

    fun getObjectsByType(type:Int):List<ObjectData>


}