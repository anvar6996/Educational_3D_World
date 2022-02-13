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

enum class CollectionEnum(
    val pos: Int,
    val urlImage: String
){
    History(1, "https://thedispatch.blob.core.windows.net/thedispatchimages/2021/09/History.jpg"),
    Geometry(2, "https://img3.goodfon.ru/wallpaper/nbig/d/f2/colorful-geometry-shapes-7654.jpg"),
    Zoology(3, "https://leverageedu.com/blog/wp-content/uploads/2020/01/Branches-of-Zoology.png"),
    Astronomy(4, "https://skyandtelescope.org/wp-content/uploads/Solar-system-NASA_S-630x338.jpg"),
    Music(5, "http://musicedhighlights.files.wordpress.com/2011/03/2010-clip-art.gif")
}