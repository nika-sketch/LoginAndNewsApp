package ge.nlatsabidze.newsapplication.data.model

import androidx.room.Entity
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.PrimaryKey
import ge.nlatsabidze.newsapplication.core.Mapper
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.IgnoredOnParcel

@Entity(tableName = "Article")
@Parcelize
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    @Embedded val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id1: Int = 0
}

