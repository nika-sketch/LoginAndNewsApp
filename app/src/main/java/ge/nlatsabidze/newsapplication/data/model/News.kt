package ge.nlatsabidze.newsapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val articles: MutableList<Article>?,
    val status: String?,
    val totalResults: Int?
): Parcelable
