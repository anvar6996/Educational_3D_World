package com.example.educational3dworld.domain.repository.impl

import com.example.educational3dworld.data.models.ObjectData
import com.example.educational3dworld.domain.repository.AppRepository
import com.google.firebase.firestore.FirebaseFirestore
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


    override fun getObjectsByType(type: Int): List<ObjectData> {
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
        return list

    }

}