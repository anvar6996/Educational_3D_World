package com.example.educational3dworld.domain.repository.impl

import com.example.educational3dworld.data.models.ObjectData
import com.example.educational3dworld.domain.repository.AppRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
) : AppRepository {
    override var collectionsType: Map<Int, String> = mapOf(
        1 to "history", 2 to "Geometry", 3 to "Zoologiya"
    )
    private var successLoadListener: (() -> Unit)? = null
    override fun successLoadListener(block: () -> Unit) {
        successLoadListener = block
    }


    override fun getObjectsByType(type: Int): Flow<List<ObjectData>> = flow {
        val list = ArrayList<ObjectData>()
        fireStore.collection(collectionsType.getValue(type))
            .get()
            .addOnSuccessListener { result ->
                result.forEach { item ->
                    val id = item["id"] as Int
                    val name = item["name"] as String
                    val type = item["type"] as Int
                    val fileUrl = item["fileUrl"] as String
                    list.add(ObjectData(id, name, fileUrl, type))
                }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {

            }
        emit(list)
    }.flowOn(Dispatchers.IO)

}