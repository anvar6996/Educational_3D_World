package com.example.educational3dworld.domain.repository.impl

import com.example.educational3dworld.data.models.CollectionData
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
        1 to "history", 2 to "Geometry", 3 to "Zoologiya", 4 to "Astronomy", 5 to "Music"
    )
    override var collectionImage: List<CollectionData> = listOf(
        CollectionData(1,"https://thedispatch.blob.core.windows.net/thedispatchimages/2021/09/History.jpg"),
        CollectionData(2,"https://img3.goodfon.ru/wallpaper/nbig/d/f2/colorful-geometry-shapes-7654.jpg"),
        CollectionData(3,"https://leverageedu.com/blog/wp-content/uploads/2020/01/Branches-of-Zoology.png"),
        CollectionData(4,"https://skyandtelescope.org/wp-content/uploads/Solar-system-NASA_S-630x338.jpg"),
        CollectionData(5,"http://musicedhighlights.files.wordpress.com/2011/03/2010-clip-art.gif")
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
                    val id = item["id"] as Long
                    val name = item["name"] as String
                    val type = item["type"] as Long
                    val fileUrl = item["fileUrl"] as String
                    val image = item["image"] as String
                    list.add(ObjectData(id, name, fileUrl, type,image))
                }
                successLoadListener?.invoke()
            }
            .addOnFailureListener {

            }
        emit(list)
    }.flowOn(Dispatchers.IO)

}