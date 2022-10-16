package ge.nlatsabidze.newsapplication.data.model

import androidx.room.Entity
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import ge.nlatsabidze.newsapplication.core.Mapper
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.IgnoredOnParcel

@Entity(tableName = "Article")
data class Article(
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "publishedAt") val publishedAt: String?,
    @Embedded val source: Source?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?
) {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id1: Int = 0
}

