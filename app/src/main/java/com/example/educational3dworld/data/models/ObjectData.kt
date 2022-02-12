package com.example.educational3dworld.data.models

data class ObjectData(
    val id: Long,
    val name: String,
    val fileUrl: String,
    val type: Long,
    val image:String
)

enum class TypeEnum(
    val pos: Int,
    val text: String
) {
    History(1, "History"),
    Geometry(2, "Geometry"),
    Zoology(3, "Zoology"),
    Astronomy(4, "Astronomy"),
    Music(5, "Music")
}