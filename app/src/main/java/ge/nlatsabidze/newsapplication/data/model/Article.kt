package ge.nlatsabidze.newsapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Parcelable {
//    fun toUiArticle(): UiArticle {
//        return UiArticle.Base()
//    }
}

//interface UiArticle {
//
//    class Base(
//        val author: String,
//        val content: String,
//        val description: String,
//        val publishedAt: String,
//        val source: Source,
//        val title: String,
//        val url: String,
//        val urlToImage: String
//    ) : UiArticle {
//
//    }
//}