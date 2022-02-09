package com.example.educational3dworld.data.models

data class ObjectData(
    val id: Int,
    val name: String,
    val fileUrl: String,
    val type: Int
)

enum class TypeEnum(
    val pos: Int,
    val text: String
) {
    History(1, "History"),
    Geometry(2, "Geometry"),
    Zoology(3, "Zoology")
}